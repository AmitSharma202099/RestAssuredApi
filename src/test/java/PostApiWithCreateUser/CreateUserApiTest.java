package PostApiWithCreateUser;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserApiTest {
	
	@Test
	public void postCreateUserTest_WithJSONFile() {
			
		RestAssured.baseURI = "https://gorest.co.in";
		Integer userId= given().log().all()
				.headers("Authorization","Bearer 095b5911d3bdc3df13cfed2726e031a8e9b66159533802c59fa814bfcaec4c2c")
			//.header("Content-Type","Content-Type: application/json")
			.contentType(ContentType.JSON)
			//.body(new File(".\\src\\test\\resources\\jsons\\user.json"))
			.body(new File("./src/test/resources/jsons/user.json"))
			.when().log().all()
			.post("/public/v2/users")
			.then().log().all()
			.assertThat()
			.statusCode(201)
			.extract()
			.path("id");
	
	System.out.println("User ID :" + userId);	
	Assert.assertNotNull(userId);
		
	//Expected status code <200> but was <422>.
	// 422 means could not process
		}
	
	
		@Test
		public void getAPiQueruPathParamNestedTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.headers("Authorization","Bearer 095b5911d3bdc3df13cfed2726e031a8e9b66159533802c59fa814bfcaec4c2c")
			//.pathParam("userid", userid)
			.when().log().all()
				.get("/public/v2/users/")	
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
