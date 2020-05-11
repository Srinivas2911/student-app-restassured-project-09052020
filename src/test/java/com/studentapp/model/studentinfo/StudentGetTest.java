package com.studentapp.model.studentinfo;

/*
Created by SP
*/

import com.studentapp.model.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StudentGetTest extends TestBase {

    @Test
    public void getAllStudentInfo() {
        Response response = given()
                .log().method()
                .when()
                .get("/list");
        response.then().statusCode(200)
                .log().all();
        //      response.prettyPrint();

    }

    @Test
    public void searchStudentById() {
        given()
                .log().method()
                .when()
                .get("7")
                .then()
                .log().all()
                .statusCode(200);
        get("/list")
                .then()
                .body("[6].id", equalTo(7), "[6].firstName", equalTo("Roth"), "[6].lastName", equalTo("Grant"),
                        "[6].email", equalTo("eu.tellus@anuncIn.edu"), "[6].programme", equalTo("Mechanical Engineering"));

    }

    @Test
    public void searchStudentWithProgramme() {
        given()
                .log().parameters()
                .queryParam("programme", "Computer Science")
                .when()
                .get("/list")
                .then().statusCode(200).log().body()
                .body("programme.size()", equalTo(14));

        //response.prettyPrint();

    }

    @Test
    public void searchStudentWithProgrammeAndLimit() {
        given()
                .log().params()
                .param("programme", "Financial Analysis")
                .param("limit", 2)
                .when()
                .get("/list")
                .then().statusCode(200).log().body()
                .body("programme.size()", equalTo(2));

    }
}
