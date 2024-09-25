package BookingApiWFTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BookingApiFeatureTest {
	
	String tokenId; 
		
	
	@BeforeMethod
	public void getTokenTest() {
		
		//Credentials cred = new Credentials("admin","password123");
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		 tokenId = given()
			.contentType(ContentType.JSON)
				//.body(cred)
				  .body(new File(".\\src\\test\\resources\\jsons\\auth.json"))
					.when()
						.post("/auth")
							//.getStatusCode();
							.then()
								.assertThat()
									.statusCode(200)
										.extract()
											.path("token");
											
				//System.out.println("Status Code is:" + statuscode);
				  System.out.println("Token Code is:" + tokenId);

	}
	
	
	@Test
	public void getAllBookingIdTest() {
		
		//RestAssured.baseURI = ("https://restful-booker.herokuapp.com");
		
		Response  response = given()
			.contentType(ContentType.JSON)
				.when().log().all()
					.get("/booking")
						.then()
							.assertThat()
								.statusCode(200)
									.extract()
										.response();
										//.path("bookingid");
		

		JsonPath js= response.jsonPath();
		List<Integer> allBids =js.getList("bookingid");
		System.out.println("Count of Ids :" + allBids.size());
 
		for (Integer i : allBids) { //-->Recommended 
			Assert.assertNotNull(i, "ID is NULL");
			//System.out.println("ID Value is :" +  i);
			//Count of Ids :1771
		}
		

	}

	
	
	@Test
	public void getBookingIdTest() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		//Create a new bookingId : POST
		int newBookingId = createBooking();
		
		//get the same bookingId :GET
		given().log().all()
			.pathParams("bookingid", newBookingId)
				.when().log().all()
					.get("/booking/{bookingid}")
						.then().log().all()
							.assertThat()	
								.statusCode(200)
									.and()
										.body("firstname", equalTo("Jim"))
										.and()
										.body("bookingdates.checkin", equalTo("2018-01-01"));
	}
	

	@Test
	public void createBooking_POSTTest() {
		Assert.assertNotNull(createBooking());
	}
	
	@Test
	public void updateBookingTest() {
		//Create a new bookingId : POST
		int newBookingId = createBooking();
		
		//get the same bookingId :GET
		  verifyBookingId(newBookingId);
				
		//update same booking id :PUT
				
				given().log().all()
				.pathParams("bookingid", newBookingId)
				.contentType(ContentType.JSON)
				.header("Cookie", "token=" + tokenId)
				.body("{\n"
						+ "    \"firstname\" : \"Amit\",\r\n"
						+ "    \"lastname\" : \"Sharma\",\r\n"
						+ "    \"totalprice\" : 111,\r\n"
						+ "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n"
						+ "        \"checkin\" : \"2024-01-01\",\r\n"
						+ "        \"checkout\" : \"2024-01-10\"\r\n"
						+ "    },\r\n"
						+ "    \"additionalneeds\" : \"Lunch\"\r\n"
						+ "}")
					.when().log().all()
						.put("/booking/{bookingid}")
							.then().log().all()
								.assertThat()	
									.statusCode(200)
									.body("firstname", equalTo("Amit"))
									.and()	
									.body("lastname", equalTo("Sharma"))
									.and()	
									.body("additionalneeds", equalTo("Lunch"));
	
		
			
	}
	
	@Test
	public void partialBookingTest() {
		//Create a new bookingId : POST
		int newBookingId = createBooking();
		
		//get the same bookingId :GET
	      verifyBookingId(newBookingId);
				
		//update same booking id :PATCH
				
				given().log().all()
				.pathParams("bookingid", newBookingId)
				.contentType(ContentType.JSON)
				.header("Cookie", "token=" + tokenId)
						.body("{\n"
								+ "    \"firstname\" : \"API\",\r\n"
								+ "    \"lastname\" : \"Automation\"\r\n"
								+ "}")
						.when().log().all()
						.patch("/booking/{bookingid}")
							.then().log().all()
								.assertThat()	
									.statusCode(200)
									.and()
									.body("firstname", equalTo("API"))
									.and()	
									.body("lastname", equalTo("Automation"));
			
	}
	

	@Test
	public void deleteBooking_DELTest() {
		//Create a new bookingId : POST
		int newBookingId = createBooking();
		
		//get the same bookingId :GET
		verifyBookingId(newBookingId);
				
		//update same booking id :DELETE
				
				given().log().all()
				.pathParams("bookingid", newBookingId)
				.contentType(ContentType.JSON)
				.header("Cookie", "token=" + tokenId)
					.when().log().all()
						.delete("/booking/{bookingid}")
							.then().log().all()
								.assertThat()	
									.statusCode(201);	
	}
	
	
	
	public void verifyBookingId(int newBookingId) {
		
		//get the same booking id: GET
		given()
			.pathParam("bookingId", newBookingId)
				.when().log().all()
					.get("/booking/{bookingId}")
						.then().log().all()
							.assertThat()
								.statusCode(200);
		
	}
	
	public int createBooking() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		int bookingid = given()
			.contentType(ContentType.JSON)
				.body("{\n"
						+ "    \"firstname\" : \"Jim\",\r\n"
						+ "    \"lastname\" : \"Brown\",\r\n"
						+ "    \"totalprice\" : 111,\r\n"
						+ "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n"
						+ "        \"checkin\" : \"2018-01-01\",\r\n"
						+ "        \"checkout\" : \"2019-01-01\"\r\n"
						+ "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
						+ "}")
				.when()
					.post("/booking")
						.then()
							.extract()
								.path("bookingid");
		
		
		//JsonPath js = bid.jsonPath();
		//int bookingid = js.getInt("bookingid");
		
		System.out.println("****Booking ID :" + bookingid);
		return bookingid;
	}	
	
}
			
		
	
