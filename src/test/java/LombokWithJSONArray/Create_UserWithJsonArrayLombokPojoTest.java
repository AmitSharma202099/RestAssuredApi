package LombokWithJSONArray;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class Create_UserWithJsonArrayLombokPojoTest {

    @Test
  public void CreateUserWithJSONArrayPOJOTest(){

      //RestAssured.baseURI="https://httpbin.org/";
      RestAssured.baseURI="https://postman-echo.com/";
        User.UserData userData1 = new User.UserData(1,"amit1.gmail","amit1","sharma1","SDET1");
        User.UserData userData2 = new User.UserData(1,"amit2.gmail","amit2","sharma2","SDET2");
        User.UserData userData3 = new User.UserData(1,"amit3.gmail","amit3","sharma3","SDET3");
        User.UserData userData4 = new User.UserData(1,"amit4.gmail","amit4","sharma4","SDET4");
        User.UserData userData5 = new User.UserData(1,"amit5.gmail","amit5","sharma5","SDET5");
        User.UserData userData6 = new User.UserData(1,"amit6.gmail","amit6","sharma6","SDET6");

        User.Support support = new User.Support("facebook.com","first page");
        User user = new User(1,
                10,
                20,
                200,
                Arrays.asList(userData1,userData2,userData3,userData4,userData5,userData6),
                java.util.List.of(support));

        given().log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when().log().all()
                .post("/post")
                .then().log().all()
                .assertThat()
                .statusCode(200);







  }

    @Test
    public void createUserWithJSONArrayBuilderTest(){

        //RestAssured.baseURI="https://httpbin.org/";
        RestAssured.baseURI="https://postman-echo.com/";

        User.Support support = new User.Support.SupportBuilder()
                .url("https://facebook.com")
                .text("Automation")
                .build();

        User.UserData userData = new User.UserData.UserDataBuilder()
                .email("amit@gmail.com")
                .first_name("Amit")
                .last_name("Sharma")
                .avatar("SDET")
                .build();

        User user = new User.UserBuilder()
                .page(10)
                .per_page(1)
                .total(20)
                .total_pages(200)
                // .support(List<support> support)
                .build();;

        given().log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when().log().all()
                .post("/post")
                .then().log().all()
                .assertThat()
                .statusCode(200);


    }

}
