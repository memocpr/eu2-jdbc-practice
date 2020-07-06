package apitests;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.testng.Assert.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

public class SpartenTestWithParametersX {

    @BeforeClass
    public void beforeClass(){

        RestAssured.baseURI = ConfigurationReader.getProperty("spartanapi_url");
    }




    /*
   Given accept type is Json
   And Id parameter value is 5
   When user sends GET request to /api/spartans/{id}
   Then response status code should be 200
   And response content-type: application/json;charset=UTF-8
   And "Blythe" should be in response payload
*/
    //payload = body
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .pathParam("id", "5")
                .when().get("/api/spartans/{id}");

        response.prettyPrint();

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        assertTrue(response.body().asString().contains("Blythe"));
    }

     /*
   TASK
   Given accept type is Json
   And Id parameter value is 500
   When user sends GET request to /api/spartans/{id}
   Then response status code should be 404
   And response content-type: application/json;charset=UTF-8
   And Spartan Not Found" message should be in response payload
*/


    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .pathParam("id", "500")
                .get("/api/spartans/{id}");

        assertEquals(response.statusCode(),404);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Spartan Not Found"));

    }

   /*
    Given accept type is Json
    And query parameter values are :
    gender|Female
    nameContains|e
    When user sends GET request to /api/spartans/search
    Then response status code should be 200
    And response content-type: application/json;charset=UTF-8
    And "Female" should be in response payload
    And "Janette" should be in response payload
 */

    @Test
    public void test3(){

        Response response = given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .queryParam("gender", "Female")
                .queryParam("nameContains", "e")
                .get("/api/spartans/search");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        //verify body contains
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));

    }


    @Test
    public void test4(){

        Map<String,Object> queryMap=new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        Response response = given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .queryParams(queryMap)
                .get("/api/spartans/search");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        //verify body contains
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));


    }
    /*
      Given accept type is json
      And path param id is 10
      When user sends a get request to "api/spartans/{id}"
      Then status code is 200
      And content-type is "application/json;char"
      And response payload values match the following:
              id is 10,
              name is "Lorenza",
              gender is "Female",
              phone is 3312820936
       */
    @Test
    public void test5(){

        Response response = given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .pathParam("id", "10")
                .get("api/spartans/{id}");
        response.prettyPrint();

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone=response.path("phone");

        assertEquals(id,10);
        assertEquals(name,"Lorenza");
        assertEquals(gender,"Female");
        assertEquals(phone,3312820936l);
    }

}
