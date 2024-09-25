package GETAPITests;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GETAPIWIthQueryParams {

	/*
	 * @BeforeMethod public Map<String, String> getqueryMap() {
	 * 
	 * Map<String,String> queryMap = new HashMap<String,String>(); 
	 * queryMap.put("firstname","Naveen"); 
	 * queryMap.put("country","USA"); }
	 */

	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

	}

	@Test

	public void getUserAPiQueryParams() {

		// RestAssured.baseURI = "https://gorest.co.in";

		given().log().all().header("AUthorization",
				"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NDg3MWE2NmY2ZDEzYzAwMTM3Y2IzMWEiLCJpYXQiOjE3MjY3MDMyMjJ9.sSJO2ZENTWAX7Pn7lj19_xYVAADZeUozyqPlxORCLjo")
				.queryParam("firstname", "Naveen")
				.queryParam("country", "USA")
					.when().log().all()
				// .get("/public/v2/users")
					.get("/contacts")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON);

	}

	@Test
	public void getUserAPiQueryParamWithHashMap() {

		
		/*
		 * Map<String,String> queryMap = new HashMap<String,String>();
		 * queryMap.put("firstname","Naveen"); queryMap.put("country", "USA");
		 */
		 
		Map<String,String> queryMap = Map.of(  // this is called immutable hash map -->Preferred
					"firstname","Naveen",
					"country","USA"
			);

		given().log().all().header("AUthorization",
				"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NDg3MWE2NmY2ZDEzYzAwMTM3Y2IzMWEiLCJpYXQiOjE3MjY3MDMyMjJ9.sSJO2ZENTWAX7Pn7lj19_xYVAADZeUozyqPlxORCLjo")
				.queryParams(queryMap)
					.when().log().all()
				// .get("/public/v2/users")
						.get("/contacts")
							.then()
								.log().all()
									.assertThat()
										.statusCode(200)
											.and()
												.contentType(ContentType.JSON);
	}
	
	
	@Test
	public void getSingleUserName_FetchFullUserData() {
		
		Map<String,String> queryMap = Map.of(  // this is called immutable hash map -->Preferred
				"firstName","Naveen",
				"country","USA",
				"phone","8005555555"
		);
		
	Response resp	= given().log().all()
			.header("AUthorization",
				"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NDg3MWE2NmY2ZDEzYzAwMTM3Y2IzMWEiLCJpYXQiOjE3MjY3MDMyMjJ9.sSJO2ZENTWAX7Pn7lj19_xYVAADZeUozyqPlxORCLjo")
					.queryParams(queryMap)
						.when().log().all()
							.get("/contacts");
		
		System.out.println("Status Code is " + resp.statusCode());
		System.out.println("Status Code is " + resp.statusLine());
		
		JsonPath jres =  resp.jsonPath();
		
		List<String> fname= jres.getList("firstName");
		System.out.println("firstName is " + fname);
		Assert.assertTrue(fname.contains("Naveen"));
		
		List<String> countryname = jres.get("country");
		System.out.println("CountryName is " + countryname);
		
		for(String s :fname) {
			System.out.println("First Names is    " + s);
		}
	
		

	}
	
	

}
