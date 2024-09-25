package GETAPITests;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.*;


public class GetPathParamsNestedResources_GoRestAPI {
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"7413164"},
			{"7413163"},
			{"7413162"}
		};
	}
	
	@Test(dataProvider="getUserData")
	
	public void getAPiQueruPathParamNestedTest(String userid) {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.headers("Authorization","Bearer 095b5911d3bdc3df13cfed2726e031a8e9b66159533802c59fa814bfcaec4c2c")
			.pathParam("userid", userid)
			.when().log().all()
				.get("/public/v2/users/{userid}/posts")	
				.then().log().all()
				//.thenReturn()
				//.prettyPeek();
				.assertThat()
				.statusCode(200);
				//.and()
				//.body("title", contains("Iste sed reiciendis sono sapiente vitium."));
				//.body("title", hasItem("Iste sed reiciendis sono sapiente vitium."));

					//equalTo :json object {}
					//hasItem :Json array []
	}
	

}
