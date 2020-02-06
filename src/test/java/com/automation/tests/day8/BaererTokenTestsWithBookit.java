package com.automation.tests.day8;
import com.automation.pojos.Spartan;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import com.automation.pojos.Job;
import com.automation.pojos.Location;
import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class BaererTokenTestsWithBookit {

    @BeforeAll
    public static void before(){
        baseURI = ConfigurationReader.getProperty("bookit.qa1");
    }

    // Let's get list of all rooms and verfy that status code is 200

    @Test
    @DisplayName("Get list of all rooms")
    public void test1(){
        Response response = given().header("Authorization",getToken()).
                when().
                get("/api/rooms").prettyPeek();


        //second solution
        given().auth().oauth2(getToken()).
                get("/api/rooms").prettyPeek();
    }

    public String getToken(){
        Response response = given().
                queryParam("email",ConfigurationReader.getProperty("teacher.email")).
                queryParam("password",ConfigurationReader.getProperty("teacher.password")).
                when().
                get("/sign").prettyPeek();

        return response.jsonPath().getString("accessToken");

    }




}
