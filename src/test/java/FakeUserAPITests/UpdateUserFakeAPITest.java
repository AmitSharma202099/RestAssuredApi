package FakeUserAPITests;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;


public class UpdateUserFakeAPITest {
	
	
	
	@Test				
	public void createUserTest_LombokPOJO() {
		
		//Geolocation gloc = new Geolocation(null, null)
		FakeUser.Address.Geolocation geolocation = new FakeUser.Address.Geolocation();
		FakeUser.Address address = new FakeUser.Address("Bengalure", "Marthalli", 9, "560037", geolocation);
		FakeUser.Name name = new FakeUser.Name("AMit", "Sharma");
		FakeUser user = new FakeUser("amit@gmail", "Amit", "password123", "8552006999", name, address);
		
		
			RestAssured.baseURI = "https://fakestoreapi.com";
				
		int id=	given().log().all()
				.contentType(ContentType.JSON)
					.body(user)
						.when().log().all()
							.post("/users")
								.then().log().all()
									.assertThat()
										.statusCode(200)
											.and()
												.extract()
													.path("id");
			
			Assert.assertNotNull(id);
			System.out.println("ID Created is :" + id);				
	}
	
	@Test				
	public void createUserTest_LombokBuilder() {
		
			RestAssured.baseURI = "https://fakestoreapi.com";
			
			FakeUser.Address.Geolocation geolocation = new FakeUser.Address.Geolocation.GeolocationBuilder()
					.lat("-97.315")
					.longitude("98.1496")
					.build();
			
			//FakeUser.Address address = new FakeUser.Address.AddressBuilder()
			FakeUser.Address address = new FakeUser.Address.AddressBuilder()
					.city("Banglore")
						.street("Thubarhalli")
							.number(10)
								.zipcode("560066")
									.geolocation(geolocation)
										.build();
						
			
			//FakeUser.Name name = new FakeUser.Name().builder()
			FakeUser.Name name = new FakeUser.Name.NameBuilder()
					.firstname("Saumya")
						.lastname("Mishra")
							.build();
			
			FakeUser user = new FakeUser.FakeUserBuilder()
					.email("Saumya@gmail.com")
						.username("Saumya")
							.password("password123")
								.phone("9999888810")
									.name(name)
										.address(address)
											.build();
					
		
		int id=	given().log().all()
				.contentType(ContentType.JSON)
					.body(user)
						.when().log().all()
							.post("/users")
								.then().log().all()
									.assertThat()
										.statusCode(200)
											.and()
												.extract()
													.path("id");
			
			Assert.assertNotNull(id);
			System.out.println("ID Created is :" + id);				
	}

}
