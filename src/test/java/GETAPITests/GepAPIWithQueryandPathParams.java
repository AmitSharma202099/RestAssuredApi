package GETAPITests;

import static io.restassured.RestAssured.given;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GepAPIWithQueryandPathParams {
	
	
	@Test
	public void getApiQueryandPathParama_SingleUser() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		Map<String,String> queryMap = Map.of(  // this is called immutable hash map -->Preferred
				"name","Amit",
				"status","active"
		);
		
	Response response	= given().log().all()
			.headers("Authorization","Bearer 095b5911d3bdc3df13cfed2726e031a8e9b66159533802c59fa814bfcaec4c2c")
			.queryParams(queryMap)
				.when().log().all()
					.get("/public/v2/users/7413519")
						.thenReturn()
							.prettyPeek();
					/*
						 * then().log().all() .assertThat() .statusCode(200);
						 */
		
		JsonPath js = response.jsonPath();
		int userid = js.getInt("id");
		System.out.println("Userid is ..."+ userid);
		Assert.assertEquals(7413519, userid, "*********UserId is Invalid*******");
		//Assert.assertTrue(ids.contains(7413519));

		
		String username =js.getString("name");
		System.out.println("Username is ..." + username);
		Assert.assertEquals("Amit_V_Sharma"	,username, "****UserEmail is Invalid****");
		
					
	}
	
	
	@Test
	public void getAPiQuery_MutiUser() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		Response resp = given().log().all()
			.headers("Authorization","Bearer 095b5911d3bdc3df13cfed2726e031a8e9b66159533802c59fa814bfcaec4c2c")
				.when().log().all()
					.get("/public/v2/users")
						.thenReturn()
							.prettyPeek();
		
		JsonPath  js = resp.jsonPath();
		List <Integer> ids= js.getList("id"); //List<Object
		System.out.println("Numbers of Id's Present are :" + ids);
		Assert.assertTrue(ids.containsAll(ids));
		//System.out.println({Assert.assertTrue(ids.containsAll(ids)});
		
		//iteration of ids
		for (Integer i: ids) {
			System.out.println("Ids Present: " + i);
			
		}
		
		 List<String> name= js.getList("name");
		 System.out.println("List of Names are :" + name);
		Assert.assertTrue(name.containsAll(name), "**********Matches********");
		for (String string : name) {
			System.out.println("Names Present: " + string);
		}
		 
		/* 
  Open Declaration   java.lang.Integer
The Integer class wraps a value of the primitive type int in an object. An object of type Integercontains a single field whose type is int. 
In addition, this class provides several methods for convertingan int to a String and a String to an int, as well as other constants and methods useful whendealing with an int. 
    	  	 
java.lang.Object
Class Object is the root of the class hierarchy.Every class has Object as a superclass. All objects,including arrays, implement the methods of this class.
    	 */
					
	}
	
	
	
	@Test
	public void getNestedData() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response resp = given().log().all()
			.when()
				.get("/products");
					/*.then().log().all()
						.statusCode(200);*/
		
		
       JsonPath js = resp.jsonPath();	
       
       List<Integer> ids = js.getList("id");
       // System.out.println("#######Id List are:" + ids);
       //Assert.assertTrue(ids.containsAll(ids),"****Not Matches all IDs****");
      
       List<Object> price = js.getList("price");
       //System.out.println("#######Price List are:" + price);
       
       List<String> category = js.getList("category");
       //System.out.println("#######category List are:" + category);
       
       List<Object> rate = js.getList("rating.rate");
       System.out.println("#######Rating Rate List are:" + rate);
       Assert.assertTrue(rate.contains(4.7f));
       Assert.assertTrue(rate.containsAll(rate),"Not Matched" );
       
       List<Integer> count = js.getList("rating.count");
       //System.out.println("#######Rating Count List are:" + count);
       
		/*
		 * for (Iterator iterator = count.iterator(); iterator.hasNext();) { Integer
		 * integer = (Integer) iterator.next();
		 * System.out.println("#######Rating Rate List are: " + integer);
		 * System.out.println("#######Rating Count List are: " + integer); }
		 */
       
       for (int i = 0; i < ids.size(); i++) {
    	   
    	   int id = ids.get(i);
    	   //Assert.assertTrue(true, "Not Matched");
    	   Object price1 = price.get(i);
		   String category1 = category.get(i); 
		   Object rate1= rate.get(i); 
		   int count1 =count.get(i);
			 	   
    	  System.out.println( "    Id:    " + id +"    Price :    "  + price1 + "    Category:    " + category1  + "    Rate:    "  + rate1 + "    Count:    " + count1);
    	   
		
	}
       
       
		
		/*
		 * for (Object float1 : price) { System.out.println("#######Price List are:" +
		 * float1);
		 * 
		 * }
		 */
		 
		
		
	int a =	resp.statusCode();
	String b= resp.statusLine();
	int c = resp.getStatusCode();
	String d = 	resp.getStatusLine();
	
	//System.out.println("*****statusCode:"+ a + "*****statusLine:" + b +"*****getStatusCode():" + c + "*****statusLine()" + d);
		
	String e=	resp.contentType();
	Cookies f=	resp.getDetailedCookies();
	Headers g=	resp.getHeaders();
	
	//System.out.println("#####contentType():"+ e + "#####getDetailedCookies():" + f +"#####getHeaders():" + g);

						
							
		
		
		
	}

}
