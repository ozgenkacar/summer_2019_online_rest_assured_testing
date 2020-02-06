package com.automation.tests.day7;
import com.automation.pojos.Spartan;
import com.automation.utilities.ConfigurationReader;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.transform.sax.SAXSource;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class SpartansTestsDay7 {

    @BeforeAll
    public static void setup(){
        baseURI = ConfigurationReader.getProperty("spartan.uri");
    }

    //add new Spartan from the external JSON file

    @Test
    @DisplayName("Add new user by using external Json File")
    public void test1(){
        File file = new java.io.File(System.getProperty("user.dir")+"/spartan.json");

        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(file).
                when().
                post("/spartans").
                then().assertThat().
                statusCode(201).
                body("success",is("A Spartan is Born!"));
    }
    @Test
    @DisplayName("Add new user by using map")
    public void test2(){
        Map<String, Object> spartan = new HashMap<>();
        spartan.put("phone", 12999999117L);
        spartan.put("gender", "Male");
        spartan.put("name", "John Deer");
        // you must specify content type, whenever you POST
        //contentType(ContentType.JSON)
        assertEquals("Male", spartan.get("gender"));
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(spartan).
                when().
                post("/spartans").prettyPeek().
                then().assertThat().
                statusCode(201).
                body("success", is("A Spartan is Born!")).
                body("data.name", is("John Deer")).
                body("data.gender", is("Male"));
        //in the response, we have spartan object inside data variable
        //to get properties we need to specify name of that object data
        //put . and parameter that we want to read
        // data.id , data.gender, data.name
        // success - property, string variable
        // data - object that represents spartan
    }
    @Test
    @DisplayName("update spartan,only name PATCH")
    public void test3(){
        Map<String ,Object> update = new HashMap<>();

        update.put("name","Ozgen");
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(update).
                pathParam("id",903).
        when().patch("/spartans/{id}").prettyPeek().
                then().assertThat().
                statusCode(204);
}



}
