package com.bulsoft.zoomautomation.testcases;

import com.bulsoft.zoomautomation.domain.Auth;
import com.bulsoft.zoomautomation.domain.Blog;
import com.bulsoft.zoomautomation.domain.UserDetails;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class AnotherAuthTest {

    private String authToken = "Bearer ";

    @BeforeTest
    public void setup(){
        RestAssured.baseURI = "https://pragrablog.herokuapp.com";
    }

    @BeforeClass
    public void authTest(){

        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("admin");

        ResponseBody responseBody =
                given()
                .header("Content-Type", ContentType.JSON)
                .body(auth)
                .when().post("/api/authenticate").body();


        authToken = authToken + responseBody.jsonPath().getString("id_token");


    }


    @Test
    public void testGetBlogs(){
        System.out.println();
            given().header("Authorization", authToken)
                    .header("Accept", ContentType.JSON)
                    .when()
                    .get("/api/blogs").then()
                    .statusCode(200)
                    .and()
                    .body("[0].id",equalTo(1051))
                    .and()
                    .body("[0].user.id",equalTo(5));
    }


    @Test
    public void postBlogTest(){

     UserDetails details =   given()
                .header("Authorization", authToken)
                .header("Accept", ContentType.JSON)
                .pathParam("login","admin")
                .when()
                .get("/api/users/{login}").getBody().as(UserDetails.class);

        System.out.println(details);

        Blog blog = new Blog();
        blog.setUser(details);
        blog.setHandle("tech");
        blog.setName("RestAssured Testing");
        given()
                .header("Authorization", authToken)
                .header("Accept", ContentType.JSON)
                .header("Content-Type",ContentType.JSON )
                .body(blog)
                .when()
                .post("/api/blogs").body().prettyPrint();

    }
}
