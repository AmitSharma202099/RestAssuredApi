package GETAPITests;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ProductApis {
	
	//BDD style test : given --> when --> then
	@Test
	public void getProductTest() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		given().log().all()
			.get("/products")
				.then().log().all()
					.assertThat()
						.statusCode(200);
				
		
		
	}
	
	
	@Test
	public void getProductTest2() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		given().log().all()
			.when()
				.get("/products")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.body("$.size",equalTo(20));
	}
	
	

}
