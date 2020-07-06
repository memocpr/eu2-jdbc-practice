package apitests;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetRequestX {

    String hrurl="http://18.233.65.51:1000/ords/hr/regions";

    @Test
    public void test1(){

        Response response = RestAssured.get(hrurl);

        response.prettyPrint();

    }



    /*
        Given accept type is json
        When user sends get request to regions endpoint
        Then response status code must be 200
        and body is json format
     */
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON).when().get(hrurl);

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");

    }

    @Test
    public void test3(){

        given().accept(ContentType.JSON).when().get(hrurl).then().assertThat().statusCode(200).and().contentType("application/json");
    }

    /*
        Given accept type is json
        When user sends get request to regions/2
        Then response status code must be 200
        and body is json format
        and response body contains Americas
     */
    @Test
    public void test4(){

        Response response = given().accept(ContentType.JSON).when().get(hrurl + "/2");

        Assert.assertEquals(response.statusCode(),200);

        Assert.assertEquals(response.contentType(),"application/json");

        Assert.assertTrue(response.body().asString().contains("Americas"));



    }


}
