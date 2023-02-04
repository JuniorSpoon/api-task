package com.supercassa.apitask.controller;

import com.supercassa.apitask.entity.SuperKassa;
import com.supercassa.apitask.service.SuperCassaService;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

@SpringBootTest
class SuperKassaControllerTest {

    @Autowired
    private SuperCassaService superCassaService;


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void addByTenTest() {
        String requestBody = "{\n" +
                "    \"id\": 1,\n" +
                "    \"add\": 10\n" +
                "}";
        SuperKassa kassa = superCassaService.findSupperKassaById(1);
        Integer current = kassa.getResponse().getCurrent() + 10;

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/modify")
                .then()
                .extract().response();


        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(current, response.jsonPath().getInt("current"));
    }

    @Test
    public void invalidIdValueTest() {
        String requestBody = "{\n" +
                "    \"id\": 99999999999999999999999999999999,\n" +
                "    \"add\": 1\n" +
                "}";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/modify")
                .then()
                .extract().response();

        Assertions.assertEquals(418, response.statusCode());
        Assertions.assertEquals(HttpStatus.I_AM_A_TEAPOT.name(), response.jsonPath().getString("httpStatus"));
    }

    @Test
    public void invalidAddValueTest() {
        String requestBody = "{\n" +
                "    \"id\": 1,\n" +
                "    \"add\": 99999999999999999999999999999999\n" +
                "}";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/modify")
                .then()
                .extract().response();

        Assertions.assertEquals(418, response.statusCode());
        Assertions.assertEquals(HttpStatus.I_AM_A_TEAPOT.name(), response.jsonPath().getString("httpStatus"));
    }

    @Test
    public void invalidValuesTest() {
        String requestBody = "{\n" +
                "    \"id\": 45gertw45hyewrth,\n" +
                "    \"add\": w04hsedry64u8456\n" +
                "}";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/modify")
                .then()
                .extract().response();

        Assertions.assertEquals(418, response.statusCode());
        Assertions.assertEquals(HttpStatus.I_AM_A_TEAPOT.name(), response.jsonPath().getString("httpStatus"));
    }

    @Test
    public void emptyBodyRequestTest() {
        String requestBody = "{ }";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/modify")
                .then()
                .extract().response();

        Assertions.assertEquals(418, response.statusCode());
        Assertions.assertEquals(HttpStatus.I_AM_A_TEAPOT.name(), response.jsonPath().getString("httpStatus"));
    }


}