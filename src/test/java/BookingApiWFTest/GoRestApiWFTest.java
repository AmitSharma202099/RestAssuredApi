package BookingApiWFTest;

import org.testng.annotations.DataProvider;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.contains;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class GoRestApiWFTest {
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"7413164"}
			//{"7413163"},
			//{"7413162"}
		};
	}
	
	@Test(dataProvider="getUserData")
	public void getUsersTest(String user_id) {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given()
		.headers("Authorization","Bearer 095b5911d3bdc3df13cfed2726e031a8e9b66159533802c59fa814bfcaec4c2c")
		.pathParam("user_id",user_id)	
		.when().log().all()
				.get("/public/v2/users/{user_id}")
					.then().log().all()
						.assertThat()
							.statusCode(200);
								/*.and()
									.body("id", contains(156208))
									 .and()
									 	.body("user_id", contains(7413164));*/
	}
	
	
	
	
	
	public int createUserId() {
		RestAssured.baseURI = "https://gorest.co.in";
		int id = given()
			.headers("Authorization","Bearer 095b5911d3bdc3df13cfed2726e031a8e9b66159533802c59fa814bfcaec4c2c")
				.contentType(ContentType.JSON)
					.body("{\r\n"
							+ "    \"name\": \"Amit_DDS_Sharma\",\r\n"
							+ "    \"email\": \"VAS_Oswaldo47@yahoo.com\",\r\n"
							+ "    \"gender\": \"male\",\r\n"
							+ "    \"status\": \"active\"\r\n"
							+ "}")
					.when()
						.post("/public/v2/users/")
							.then()
								.extract()
									.path("newUserId");
		
		System.out.println("User id Created is :" + id);
		return id;
	}
	
	
public void verifyBookingId(int newUserId) {
	RestAssured.baseURI = "https://gorest.co.in";
		//get the same booking id: GET
		given()
			.pathParam("bookingId", newUserId)
				.when().log().all()
					.get("/public/v2/users/{newUserId}")
						.then().log().all()
							.assertThat()
								.statusCode(200);
		
	}
	
	@Test
	public void createUserIdTest() {
		//Create a new userId : POST
		int newUserId = createUserId();
		
		
		
		
	}
	
	

}
