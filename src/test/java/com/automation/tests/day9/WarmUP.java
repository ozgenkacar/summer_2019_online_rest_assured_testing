package com.automation.tests.day9;

import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WarmUP {


    @BeforeAll
    public static void setup(){
        baseURI = ConfigurationReader.getProperty("calendarific.uri");
    }

    @Test
    @DisplayName("Verify that there are 95 holidays in the UK")
    public void test1() {
        Response response = given().accept(ContentType.JSON).
                queryParam("api_key", "2566957382c84fc09b286db9885d938e46475ce9").
                queryParam("country", "GB").
                queryParam("year", "2019").
                when().get("/holidays").prettyPeek();


        response.then().assertThat().statusCode(200);

        List<Map<String,?>> holidays = response.jsonPath().get("response.holidays");
        assertEquals(95,holidays.size());
    }
    @Test
    @DisplayName("user verifies that total number of holidays in United Kingdom is equals to 95")
    public void TC1(){
        Response response=
                given().accept(ContentType.JSON).
                        queryParam("api_key","aaf312cd592b0c7b22d90dd82e805dd4cf182dd5")
                        .when().get("/countries").prettyPeek();
        response.then().assertThat().statusCode(200);
        //Gpath - it's like Xpath, stands for searching values in JSON file.
        //GPath: response.countries.find {it.country_name == 'United Kingdom'}.total_holidays
        //find - method to find some parameter
        //{it.parameter_name == value} find JSON object that is matching criteria
        //.parameter_that_you_want = return this parameter after filtering
        int numberHolidays = response.jsonPath().get("response.countries.find {it.country_name == 'United Kingdom'}.total_holidays");

        //get all countries where number supported_languages is = 4

        List<String> countries = response.jsonPath().get("response.countries.findAll {it.supported_languages == 4}.country_name");

        System.out.println(countries);

        assertEquals(95,numberHolidays);
        System.out.println("MY TESTS PASSED SUCCESSFULLY😍😍");
    }

    @Test
    @DisplayName("user verifies that total number of holidays in United Kingdom is equals to 95")
    public void TC1_2(){
        Response response=
                given().accept(ContentType.JSON).
                        queryParam("api_key","aaf312cd592b0c7b22d90dd82e805dd4cf182dd5")
                        .queryParam("country","GB")
                        .queryParam("year",2019)
                        .when().get("/holidays").prettyPeek();
        response.then().assertThat().statusCode(200);
        List<Map<String,?>> holidays=response.jsonPath().get("response.holidays");
        assertEquals(95,holidays.size());
        System.out.println("MY TESTS PASSED SUCCESSFULLY😍😍");
    }



}
