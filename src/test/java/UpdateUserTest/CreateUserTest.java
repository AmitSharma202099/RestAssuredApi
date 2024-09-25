package UpdateUserTest;

import org.testng.annotations.Test;
import UpdateUser.User;
import UpdateUser.UserLombok;
import UpdateUser.UserLombok.UserLombokBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class CreateUserTest {
	
	public String getRandonEmailId() {
		return "ApiAutomation"+System.currentTimeMillis()+"@gmail.com";
	}
	
	@Test
	public void updateUserWith_Lombok() {
			
		  //1. create user : POST 
		RestAssured.baseURI = "https://gorest.co.in";
		  UserLombok userLombok = new UserLombok("Amit",getRandonEmailId(), "male","active"); 
		  //1. POST : create a user 
		Response postResponse = given().log().all()
		  	.contentType(ContentType.JSON)
		  		.headers("Authorization","Bearer 095b5911d3bdc3df13cfed2726e031a8e9b66159533802c59fa814bfcaec4c2c")
		  			.body(userLombok)
		  				.when()
		  					.log().all()
		  						.post("/public/v2/users");
		  
		  postResponse.prettyPrint(); int userId = postResponse.jsonPath().get("id");
		  System.out.println("UserId ====>" + userId);
		  System.out.println("==========================================");
	
	}
	
	@Test
	public void updateUserWith_LombokBuilder() {
		
		//create a user class using Lombok Builder Pattern:
		UserLombok  userLombok = new UserLombok().builder()
			.name("Amit")
			.email(getRandonEmailId())
			.status("active")
			.gender("male")
			.build();
		
		  //1. create user : POST 
		RestAssured.baseURI = "https://gorest.co.in";
		  //UserLombok user = new UserLombok("Amit",getRandonEmailId(), "male","active"); 
		  //1. POST : create a user 
		Response postResponse = given().log().all()
		  	.contentType(ContentType.JSON)
		  		.headers("Authorization","Bearer 095b5911d3bdc3df13cfed2726e031a8e9b66159533802c59fa814bfcaec4c2c")
		  			.body(userLombok)
		  				.when().log().all()
		  						.post("/public/v2/users");
		  
		  postResponse.prettyPrint(); int userId = postResponse.jsonPath().get("id");
		  System.out.println("UserId ====>" + userId);
		  System.out.println("==========================================");
	
	}

}
