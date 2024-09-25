package UpdateUserTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import UpdateUser.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class UpdateUserWithPOJO {
	
	//1. Create user : POST :fetch userid
	//2. GET
	//3. Update :PUT

	
	public String getRandonEmailId() {
		return "ApiAutomation"+System.currentTimeMillis()+"@gmail.com";
	}
	
	@Test
	public void updateUserWith_POJO() {
		//1. create user : POST
		RestAssured.baseURI = "https://gorest.co.in";
		User user = new User("Amit",getRandonEmailId(), "male", "active");
		//1. POST : create a user
		Response postResponse = given().log().all()
			.contentType(ContentType.JSON)
				.headers("Authorization","Bearer 095b5911d3bdc3df13cfed2726e031a8e9b66159533802c59fa814bfcaec4c2c")
				.body(user)
				.when().log().all()
				.post("/public/v2/users");
					
		postResponse.prettyPrint();
		int userId = postResponse.jsonPath().get("id");
		System.out.println("UserId ====>" + userId);
		System.out.println("==========================================");
		
		//2. update user : PUT
		user.setName("Saumya");
		user.setGender("female");
		given().log().all()
		.contentType(ContentType.JSON)
		.headers("Authorization","Bearer 095b5911d3bdc3df13cfed2726e031a8e9b66159533802c59fa814bfcaec4c2c")
		.body(user)
			.when().log().all()
				.put("/public/v2/users/"+userId)
				//.patch("/public/v2/users/"+userId)
				.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.body("id",equalTo(userId))
										.and()
											.body("name", equalTo(user.getName()))
												.and()
													.body("gender", equalTo(user.getGender()));
									
									
		
	}
}
	
	
