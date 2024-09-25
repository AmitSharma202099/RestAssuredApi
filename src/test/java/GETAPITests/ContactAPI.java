package GETAPITests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class ContactAPI {
	
	@BeforeMethod
	public void setup() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

	}
	
	@Test
	public void getcontactApiTest() {
		
			given().log().all()
			.headers("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NDg3MWE2NmY2ZDEzYzAwMTM3Y2IzMWEiLCJpYXQiOjE3MjY2OTU0Mzl9.yMSu8O1YSIMy4seKg3Ew2QHPzFS9CCnmVlGUR1WqJuw")
				.when().log().all()
					.get("/contacts")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.statusLine("HTTP/1.1 200 OK")
											.and().
												contentType(ContentType.JSON)
												.body("$.size", greaterThanOrEqualTo(20));
												//.body("$.size", equalTo(55));
		
	}
	
	@Test
	public void getcontactApiTest_WithInvalidToken() {
		
		given().log().all()
			.headers("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9")
				.when().log().all()
					.get("/contacts")
						.then().log().all()
							.assertThat()
								.statusCode(401);

		
		
		
	}

}
