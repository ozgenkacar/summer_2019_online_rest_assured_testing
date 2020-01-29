package com.automation.tests.day2;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ExchangeRatesAPITests {
    private String baseURI = "https://api.openrates.io/";

    @Test
    public void test1(){
        Response response = given().baseUri(baseURI+"latest").get();
        assertEquals(200,response.getStatusCode());
        System.out.println(response.prettyPrint());
    }


@Test
    public void test2() {
    Response response = given().get(baseURI + "latest");
    // verify that content type is json
    assertEquals(200, response.getStatusCode());
    assertEquals("application/json", response.getHeader("Content-Type"));
    assertEquals("application/json", response.getContentType());

}

    @Test
    public void test3(){
        //get currency exchange rate for dollar.by default it is euro

        //Response response = given().get(baseURI + "latest?base=USD").;
        Response response = given().baseUri(baseURI).
                basePath("latest").
                queryParam("base", "USD").get();

        assertEquals(200, response.getStatusCode());
        System.out.println(response.prettyPrint());

    }

// verify that respond body, for latest currency rates, contains today's date(2020-01-23)
    @Test
public void test4(){

    Response response = given().baseUri(baseURI+"latest").
                    queryParam("base", "GBP").get();

    String todaysDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        assertEquals(200, response.getStatusCode());

        assertTrue(response.getBody().asString().contains(todaysDate));
        System.out.println(response.prettyPrint());
}

// let's get currency exchange rate for year 2000
// GET https://api.exchangeratesapi.io/history?start_at=2018-01-01&end_at=2018-09-01 HTTP/1.1

    @Test
    public void test5(){

        Response response = given().baseUri(baseURI+"history").
                queryParam("start_at","2000-01-01").
                queryParam("end_at","2000-12-31").
                queryParam("base", "USD").
                queryParam("symbols","EUR","GBP","CZK").
                get();
        System.out.println(response.prettyPrint());

    }

    /* scenerio:
        Given request parameter "base" is "USD"
        When user sends request to "api.openrates.io"
        Then response code should be 200
        And response body must contain "base":"USD"

        */
    @Test
    public void test6(){
        Response response = given().baseUri(baseURI+"latest").queryParam("base","USD").get();
        String body = response.getBody().asString();
        assertEquals(200,response.getStatusCode());
        assertTrue(body.contains("\"base\":\"USD\""));
    }

}
