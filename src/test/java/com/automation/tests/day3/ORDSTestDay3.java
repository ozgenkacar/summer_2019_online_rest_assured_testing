package com.automation.tests.day3;

import com.automation.utilities.ConfigurationReader;
import com.sun.codemodel.JForEach;
import com.sun.xml.xsom.impl.scd.Iterators;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.http.Header;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class ORDSTestDay3 {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI= ConfigurationReader.getProperty("ords.uri");
    }

    //accept("application/json") shortcut forheader("Accept","application/json")
    @Test
    public void test1(){
        given().
                accept("application/json").get("/employees").
        then().
                assertThat().statusCode(200).
        and().
                assertThat().contentType("application/json").
                log().all(true); //log().ifError(); sadece error oluanı gosteriyor
    }

@Test
    public void test2(){
        given().
                accept("application/json").pathParam("id",100).
        when().
                get("/employees/{id}").
        then().
                assertThat().statusCode(200).
        and().
                assertThat().body("employee_id", is(100)).
        and().
                assertThat().body("department_id",is(90)).
        and().
                assertThat().body("last_name",is("King")).
                log().all(true);
    }
// yukardaki testi asagıdaki gibi de kısaltarak yapabiliriz
    @Test
    public void test3(){
        given().
                accept("application/json").pathParam("id",100).
        when().
                get("/employees/{id}").
        then().
                assertThat().statusCode(200).
        and().
                assertThat().body("employee_id", is(100),
                "department_id",is(90),
                "last_name",is("King"))


               .log().all(true);
    }


    /**
     given path parameter is "/regions/{id}"
     when user makes get request
     and region id is equal to 1
     then assert that status code is 200
     and assert that region name is Europe
     */
    @Test
    public void test4(){
        given().
                accept("application/json").pathParam("id",1).
         when().
                get("/regions/{id}").
         then().
                assertThat().statusCode(200).
         and().
                assertThat().body("region_name", is("Europe")).time(lessThan(3L), TimeUnit.SECONDS)
                .extract().response().prettyPrint();
           //    .log().all(true); prettyPrint() yerine bu da yazılabilir
           //     .log().body(true);
    }      // "all" contains header and body.

    @Test
    public void test6(){
        JsonPath json = given().
                accept("application/json").
        when().
                get("/employees").
        thenReturn().
                jsonPath();

        String nameOfFirstEmployee = json.getString("items[0].first_name");
        String nameOfLastEmployee = json.getString("items[-1].first_name"); // -1 -last index
        System.out.println("First employee name : "+nameOfFirstEmployee);
        System.out.println("Last employee name : "+ nameOfLastEmployee);

        Map<String,String> firstEmployee = json.get("items[0]");
        System.out.println(firstEmployee);

        for (Map.Entry<String,?> entry : firstEmployee.entrySet()){
            System.out.println("key: "+ entry.getKey() +", value: " + entry.getValue());
        }

        //get and print all last names
        List<String> lastNames = json.get("items.last_name");
         for (String str:lastNames)
             System.out.println("Last name: "+str);
    }

    //get info from countries as List<Map<String, ?>>
    @Test
    public void test7(){
        JsonPath json = given().
                          accept("application/json").
                when().
                         get("/countries").prettyPeek().jsonPath();
        //prettyPeek returns response object
        //prettyPrint returns string


     //   List<Map<String,?>> allCountries = json.get("items.country_name");
       // System.out.println(allCountries);

        List<HashMap<String,?>> allCountries = json.get("items");
        System.out.println(allCountries);
        for (HashMap<String,?> map: allCountries){
            System.out.println(map);
        }
    }

    // get collection of employee's salaries
    // then sort it
    // and print

    @Test
    public void test8(){
        List<Integer> salary = given().
                accept("application/json").
                when().
                get("/employees").
                thenReturn().jsonPath().get("items.salary");
       Collections.sort(salary);
        Collections.reverse(salary);
        System.out.println(salary);

    }

    @Test
    public void test9(){
        List<String> phoneNum = given().
                accept("application/json").
                when().
                get("/employees").
                thenReturn().jsonPath().get("items.phone_number");

      phoneNum.replaceAll(p-> p.replace(".","-"));

        System.out.println(phoneNum);

    }

    /**
     Given accept type as JSON
     And path parameter is id
     When user sends get request to /locations
     Then user verifies that status code is 200
     and user verifies that location_id is 700
     and user verifies that city is Seattle
     and user verifies that state_province is Washington

     */
    @Test
    public void test10(){
        Response response = given().
                accept(ContentType.JSON).
                pathParam("id",1700).
                when().
                get("/locations/{id}");

             response.
                     then().
                     assertThat().body("location_id", is(1700)).
                     assertThat().body("postal_code",is("98199")).
                     assertThat().body("city",is("Seattle")).

                     assertThat().body("state_province",is("Washington")).statusCode(200)


                .log().all(true);
    }
}
