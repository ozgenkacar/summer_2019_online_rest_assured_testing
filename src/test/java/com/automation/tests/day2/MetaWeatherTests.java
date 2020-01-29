package com.automation.tests.day2;

import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MetaWeatherTests {
    /*
    Examples
/api/location/search/?query=san
/api/location/search/?query=london
/api/location/search/?lattlong=36.96,-122.02
/api/location/search/?lattlong=50.068,-5.316
/api/location/44418/ - London
/api/location/2487956/ - San Francisco
     */
    private String baseURI ="https://www.metaweather.com/api/";
    @Test
    public  void test1(){

        Response response = given().baseUri(baseURI+"location/search/").
                queryParam("query","istanbul").
                get();
        assertEquals(200,response.getStatusCode());
       // System.out.println(response.prettyPrint());
        response.body().prettyPrint();
    }

    @Test
    public  void test2(){

        Response response = given().baseUri(baseURI+"location/search/").
                pathParam("woeid","2344116").
                get(baseURI+"location/{woeid}");

        assertEquals(200,response.getStatusCode());
       // System.out.println(response.prettyPrint());
        response.body().prettyPrint();
    }



}
