package com.automation.tests.review;


import com.automation.pojos.Movie;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;


import com.automation.utilities.ConfigurationReader;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

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

public class Feb6Review {

    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("omdb.uri");
    }

    @Test
    @DisplayName("Find all movies titles")
    public void search_test(){
        given().
                accept(ContentType.JSON).
                queryParam("s","Terminator").
                queryParam("apikey","1f6cb166").
                when().
                get().prettyPeek();
    }

    @Test
    @DisplayName("Find movie by title")
    public void test(){
       Response response = given().
                accept(ContentType.JSON).
                queryParam("s","Terminator").
                queryParam("apikey","1f6cb166").
                when().
                get().prettyPeek();

//To get something from payload, in case of JSON, I use JsonPath
        //collect all titles
       List<String> titles =response.jsonPath().getList("Search.Title");
        System.out.println(titles);

        //collect all poster links
        List<String> posterLinks=response.jsonPath().getList("Search.Poster");
        System.out.println(posterLinks);

    String firstTitle = response.jsonPath().getString("Search[0].Title");
        String lastTitle = response.jsonPath().getString("Search[-1].Title");

        Movie firstMovie = response.jsonPath().getObject("Search[0]",Movie.class);
        System.out.println(firstMovie);
        System.out.println(firstMovie.getYear());
        System.out.println(firstMovie.getTitle());

        List<Movie> movies = response.jsonPath().getList("Search",Movie.class);

        //print only imdb id's of every movie
        for(Movie movie: movies){
            System.out.println(movie.getImdbID());
        }
        //print all titles
        for(Movie movie : movies){
            System.out.println(movie.getTitle());
        }
    }


}
