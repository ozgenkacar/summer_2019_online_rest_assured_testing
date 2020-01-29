package com.automation.tests.day2;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSTests {

    private String baseURL ="http://ec2-54-91-251-176.compute-1.amazonaws.com:1000/ords/hr";

    //verify that status code = 200
    @Test
    public void test1(){
       // Response response = given().get("http://ec2-54-91-251-176.compute-1.amazonaws.com:1000/ords/hr/employees/");
        Response response = given().get(baseURL+"/employees");

       System.out.println(response.getBody().asString());
        assertEquals(200,response.getStatusCode());
        System.out.println(response.prettyPrint());

    }

    // get employee with id 100 and verify that status code is 200
    @Test
    public void test2(){
        Response response = given().header("Accept","applicaton/json").get(baseURL+"/employees/100");
        System.out.println(response.getBody().asString());
        assertEquals(200,response.getStatusCode());
        System.out.println(response.prettyPrint());
    }

@Test
    public void test3(){
    Response response = given().get(baseURL+"/regions");
    assertEquals(200,response.getStatusCode());
    Header header = response.getHeaders().get("Content-Type");
    for(Header h:response.getHeaders()){
        System.out.println(h);
    }
    System.out.println("----------------------------------");
    System.out.println(response.prettyPrint());
}





}
