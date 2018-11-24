package com.bulsoft.zoomautomation.testcases;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RestAPITest {

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://pragrablog.herokuapp.com";
        RestAssured.basePath = "/api";
    }

    @Test
    public void authTest(){
        JsonObject object = new JsonObject();
        object.addProperty("username","admin");
        object.addProperty("password","admin");
        Response response = RestAssured.given().header(
                "Content-Type","application/json"
        )
                .body(object.toString())
                .post("/authenticate");
        System.out.println((String) response.body().jsonPath().get("id_token"));
    }
}
