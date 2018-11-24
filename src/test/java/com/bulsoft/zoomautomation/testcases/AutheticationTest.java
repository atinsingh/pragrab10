package com.bulsoft.zoomautomation.testcases;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class AutheticationTest {

    private String token;
    @BeforeClass
    public void setUp(){
        RestAssured.baseURI = "http://pragrablog.herokuapp.com/";
        RestAssured.basePath = "/api";
    }

    @Test
    public void testAuth(){

        JsonObject requestBody = new JsonObject();

        requestBody.addProperty("username","admin");
        requestBody.addProperty("password","admin");

        token = RestAssured.given().header("Content-type", "application/json")
                .body(requestBody.toString())
                .post("/authenticate").body().jsonPath().getString("id_token");
        Assert.assertNotNull(token);

    }

    @Test(dependsOnMethods = "testAuth")
    public void readUser(){
        String response = RestAssured.given().header("Authorization", "Bearer " + token)
                .get("/users").prettyPrint();

        System.out.println(response);
    }
}
