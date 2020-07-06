package apitests;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.testng.Assert.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

public class SpartenGetRequestX {
    /* TASK
       When users sends a get request to /api/spartans/3
       Then status code should be 200
       And content type should be application/json;charset=UFT-8
       and json body should contain Fidole
    */


    String spartanUrl="http://18.233.65.51:8000";

    @Test
    public void test1(){


        Response response = given().accept(ContentType.JSON).and()
                .auth().basic("admin", "admin")
                .when().get(spartanUrl + "/api/spartans/3");

        response.prettyPrint();

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json;charset=UTF-8");
        assertTrue(response.body().asString().contains("Fidole"));

    }

    /*
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be "text/plain;charset=UTF-8"
        And header should contain date
        And Content-Length should be 17
        And body should be "Hello from Sparta"
        */

    @Test
    public void test2(){

        Response response = get(spartanUrl + "/api/hello");

        response.prettyPrint();

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"text/plain;charset=UTF-8");
        assertTrue(response.headers().hasHeaderWithName("Date"));
       assertEquals(response.header("Content-Length"),"17");
        assertTrue(response.body().asString().contains("Hello from Sparta"));

    }
}
