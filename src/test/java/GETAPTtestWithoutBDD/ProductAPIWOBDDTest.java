package GETAPTtestWithoutBDD;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ProductAPIWOBDDTest {
	
	@Test
	public void getproductApiTest() {
		
		
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		RequestSpecification request =  RestAssured.given();
		
		request.header("Authorization",
				"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NDg3MWE2NmY2ZDEzYzAwMTM3Y2IzMWEiLCJpYXQiOjE3MjY2OTU0Mzl9.yMSu8O1YSIMy4seKg3Ew2QHPzFS9CCnmVlGUR1WqJuw");
		
  
		Response response =  request.get("/contacts");
		int statusCode = response.statusCode();
		System.out.println("Status Code is " + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		String statusLine = response.statusLine();
		System.out.println("Status Line is " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

		
		
	}

}
