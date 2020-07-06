package apitests;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.testng.Assert.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartenTestWithParameters {

    @BeforeClass
    public void beforeClass(){

        RestAssured.baseURI="http://18.233.65.51:8000";
    }

    @Test
    public void test1(){

        when().get("/api/spartans");
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
    public void getSpartanId_Positive_PathParam(){

        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .and().pathParam("id",5)
                .when().get("/api/spartans/{id}");

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
    public void getSpartanId_Negetive_PathParam(){

        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .and().pathParam("id",500)
                .when().get("/api/spartans/{id}");

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
    public void positiveTestWithQueryParam(){
        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .and().queryParam("gender", "Female")
                .queryParam("nameContains", "e")
                .when().get("/api/spartans/search");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        //verify body contains
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
    }


    @Test
    public void positiveTestWithQueryParamWithMaps(){
        //create a map and add parameters
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        Response response = given().accept(ContentType.JSON).and()
                .auth().basic("admin", "admin")
                .queryParams(queryMap)
                .when().get("/api/spartans/search");

        //response verification
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
    public void getOneSpartan_path(){
        Response response = given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                .and().pathParam("id", 10)
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");

        //print each key value from jsonbody
        System.out.println(response.body().path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.body().path("gender").toString());
        System.out.println(response.path("phone").toString());

        //save json values
        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        //assert one by one
        assertEquals(id,10);
        assertEquals(name,"Lorenza");
        assertEquals(gender,"Female");
        assertEquals(phone,3312820936l);



    }
}



