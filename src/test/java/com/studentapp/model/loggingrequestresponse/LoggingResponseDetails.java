package com.studentapp.model.loggingrequestresponse;

import com.studentapp.model.StudentPojo;
import com.studentapp.model.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by SP
 */
public class LoggingResponseDetails extends TestBase {


    /**
     * This test will print out all the Response Headers
     */
    @Test
    public void test001() {
        System.out.println("---------------Printing Response Headers------------------");
        Response response = given()
                .when()
                .get("8");
        response.then()
                .log().headers()
                .statusCode(200);

    }


    /**
     * This test will print the Response Status Line
     */
    @Test
    public void test002() {
        System.out.println("---------------Printing Response Status Line------------------");
//Homework
        Response response = given()
                .when()
                .get("/list");
        response.then().statusCode(200)
                .log().status();

    }

    /**
     * This test will print the Response Body
     */
    @Test
    public void test003() {
        System.out.println("---------------Printing Response Body------------------");
//Homework
        Response response = given()
                .when()
                .get("/200");
        response.then()
                .log().body()
                .statusCode(200);
    }

    /**
     * This test will print the Response in case of an error
     */
    @Test
    public void test004() {
        System.out.println("---------------Printing Response Body in case of an error------------------");
//Homework
        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setEmail("egestas.rhoncus.Proin@massaQuisqueporttitor.org");

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .body(studentPojo)
                .patch("/16");
        response.then().log().ifError()
                .statusCode(200);


    }
}

