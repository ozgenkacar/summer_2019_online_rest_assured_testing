package com.automation.tests.day6;

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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class SpartanTests {

    @BeforeAll
    public static void setup(){
        baseURI = ConfigurationReader.getProperty("spartan.uri");

    }

    /**
     * given accept content type as JSON
     * when user sends GET request to /spartans
     * then user verifies that status code is 200
     * and user verifies that content type is JSON
     */
    @Test
    @DisplayName("Verify that /spartans end-point returns 200 and content type as JSON")
    public void test1(){
        given().
                accept(ContentType.JSON).
                when().get("/spartans").prettyPeek().
                then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON);
    }

    /**
     * given accept content type as XML
     * when user sends GET request to /spartans
     * then user verifies that status code is 200
     * and user verifies that content type is XML
     */
    @Test
    @DisplayName("Verify that /spartans end-point returns 200 and content type as XML")
    public void test2(){
        given().
                accept(ContentType.XML).
                when().get("/spartans").prettyPeek().
                then().assertThat().
                statusCode(200).
                contentType(ContentType.XML);
    }


    /** TASK
     * given accept content type as JSON
     * when user sends GET request to /spartans
     * then user saves payload in collection
     */

    @Test
    @DisplayName("Save payload into java collection")
    public void test3(){
       Response response = given().
                accept(ContentType.JSON).
                when().get("/spartans");

        List<Map<String,?>> collection = response.jsonPath().get();
for (Map<String, ?> map: collection){
    System.out.println(map);
 //   System.out.println(map.get("phone")); sadece phone numberlari yazdirmak icin
}
    }

    /** TASK
     * given accept content type as JSON
     * when user sends GET request to /spartans
     * then user save in to payload in Spartan
     */
    @Test
    @DisplayName("Save payload into java collection of Spartan")
    public void test4(){
        Response response = given().contentType(ContentType.JSON).
                when().
                get("/spartans");
        List<Spartan> collection = response.jsonPath().getList("",Spartan.class);
        for (Spartan spartan :collection){
            System.out.println(spartan);
        }


    }
    /** TASK
     * given accept content type as JSON
     * when user sends POST request to /spartans
     * then user should be able to create new spartan
     *      |gender|name           |phone     |
     *      | male |Mister Twister |5712134235|
     * then user verifies that status code is 201
     */
    //builder pattern, one of the design patterns in OOP
    //instead of having too many different constructors
    //we can use builder pattern and chain with{preopertyName} methods to specify properties of an object
@Test
    @DisplayName("Create new spartan and verify that status code is 201")
    public void test5(){

    //builder pattern, one of the design pattern and chain(propertyName) methods to specify properties of an object
    Spartan spartan1= new Spartan().
    withGender("Male").
    withName("batch12").
    withPhone(5453267865L);

    Spartan spartan = new Spartan();
    spartan.setGender("Male");
    spartan.setName("twino Twister");
    spartan.setPhone(5712134235L);

    Response response = given().
            contentType(ContentType.JSON).
            body(spartan).
            when().
            post("/spartans");
    assertEquals(201, response.getStatusCode(), "Status code is wrong!");
    assertEquals("application/json", response.getContentType(), "Content type is invalid!");
    assertEquals(response.jsonPath().getString("success"), "A Spartan is Born!");

    response.prettyPrint();

    Spartan spartan_from_response = response.jsonPath().getObject("data", Spartan.class);

    System.out.println("Spartan id: "+spartan_from_response.getspartanID());

//        //delete spartan that you've just created
//        when().delete("/spartans/{id}", spartan_from_response.getSpartanId()).
//                prettyPeek().
//                then().assertThat().statusCode(204);
}

    @Test
    @DisplayName("Delete user")
    public void test6(){
    int idOfUserThatYouWantToDelete =200;
    Response response = when().delete("/spartans/{id}",idOfUserThatYouWantToDelete);
    response.prettyPeek();
    }

    @Test
    @DisplayName("Delete user")
    public void test7(){
        int idOfUserThatYouWantToDelete =200;
        Response response = given().accept(ContentType.JSON).when().get("/spartans");
        // ı collected all user is's
        List<Integer> userId = response.jsonPath().getList("id");
        // I sorted useer id's in descending order
        Collections.sort(userId,Collections.reverseOrder());
        // I went through half of the collection, and delete half of the users
       for (int i=0;i<userId.size()/2; i++){
           when().delete("/spartans/{id}",userId.get(i));
       }
        System.out.println("After: "+userId);
    }

    @Test
    @DisplayName("Add 100 test users to Spartan app")
    public void test8(){
        Faker faker = new Faker();
        for(int i=0; i<300; i++){
            Spartan spartan = new Spartan();

            spartan.setName(faker.name().firstName());
            //remove all non-digits
            //
            String phone = faker.phoneNumber().subscriberNumber(10).replaceAll("\\D", "");
            //convert from String to Long
            spartan.setPhone(Long.parseLong(phone));

            spartan.setGender("Female");

            System.out.println(spartan);

            Response response = given().
                    contentType(ContentType.JSON).
                    body(spartan).
                    when().
                    post("/spartans");

            System.out.println(response.jsonPath().getString("success"));

         //   assertEquals(201, response.getStatusCode());

        }
    }






  @Test
      @DisplayName("Get all spartan id's and print it is as list")
public void test9(){

  }






}
