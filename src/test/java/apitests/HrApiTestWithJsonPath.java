package apitests;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.testng.Assert.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.List;

public class HrApiTestWithJsonPath {

    @BeforeClass
    public void beforeClass(){
        RestAssured.baseURI = ConfigurationReader.getProperty("hrapi_url");
    }

    @Test
    public void test(){

        Response response = get("/countries");

        JsonPath json = response.jsonPath();

        String string = json.getString("items.country_name[1]");

        System.out.println(string);

        List<Object> listOfAllIds = json.getList("items.country_id");

        System.out.println("listOfAllIds = " + listOfAllIds);



        List<String> list =json.getList("items.findAll {it.job_name==IT_PROG}.email");

        System.out.println("list = " + list);

    }

    @Test
    public void findAllEmployeesExample(){
        ///request
        Response response = given().queryParam("limit", 107)
                .get("/employees");

        assertEquals(response.statusCode(),200);

        JsonPath json = response.jsonPath();

        //get me all firstname of employees who is making more than 10000
        List<String> employeesName = json.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println(employeesName);

        //get me all emails who is working as IT_PROG;

        List<Object> emailList = json.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");

        System.out.println("emailList = " + emailList);


    }


}