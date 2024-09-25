package POSTWithAuthApi;

import org.testng.Assert;

import org.testng.annotations.Test;

import Pojo.Credentials;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;


import java.io.File;

//POJO -> Plain Old Java Object

public class AuthApiTest {
	
	@Test
	public void getAuthTokenTest_WithJSON() {
			
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		String tokenId = given().log().all()
			//.header("Content-Type","Content-Type: application/json")
			.contentType(ContentType.JSON)
			.body("{\r\n"
					+ "    \"username\" : \"admin\",\r\n"
					+ "    \"password\" : \"password123\"\r\n"
					+ "}")
			.when().log().all()
			.post("/auth")
			.then().log().all()
			.assertThat()
			.statusCode(200)
			.extract()
			.path("token");
		
		System.out.println("Token Id :" + tokenId);
		Assert.assertNotNull(tokenId);
		}
	
	@Test
	public void getAuthTokenTest_WithJSONFile() {
			
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		String tokenId = given().log().all()
			//.header("Content-Type","Content-Type: application/json")
			.contentType(ContentType.JSON)
			.body(new File(".\\src\\test\\resources\\jsons\\auth.json"))
			.when().log().all()
			.post("/auth")
			.then().log().all()
			.assertThat()
			.statusCode(200)
			.extract()
			.path("token");
		
		System.out.println("Token Id :" + tokenId);
		Assert.assertNotNull(tokenId);
		}
	
	//pojo --> json : serialization
	//json --> pojo :de-serialization
	//jackson databind/Gson, Johnzon, or Yasson
	@Test
	public void getAuthTokenTest_WithPOJO() {
		
		Credentials cred = new Credentials("admin","password123");
			
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		String tokenId = given().log().all()
			//.header("Content-Type","Content-Type: application/json")
			.contentType(ContentType.JSON)
			.body(cred)  // pojo to json : 	serialization : ObjectMapper(Jackson databind) 
			//java.lang.IllegalStateException: Cannot serialize 
			//object because no JSON serializer found in classpath.
			//Please put Jackson (Databind), Gson, Johnzon, or Yasson in the classpath.
			//Solution -> pojo to json to be done thus to serialize it : ObjectMapper -> Jackson Lib
			.when().log().all()
			.post("/auth")
			.then().log().all()
			.assertThat()
			.statusCode(200)
			.extract()
			.path("token");
		
		System.out.println("Token Id :" + tokenId);
		Assert.assertNotNull(tokenId);
		
		//json ---> pojo : De-serialization
		// lomborg no need to use getters & setters
		}

}
