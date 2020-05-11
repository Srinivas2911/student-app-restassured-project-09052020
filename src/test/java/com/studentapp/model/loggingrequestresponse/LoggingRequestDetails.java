package com.studentapp.model.loggingrequestresponse;

import com.studentapp.model.StudentPojo;
import com.studentapp.model.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

/**
 * Created by  SP
 */
public class LoggingRequestDetails extends TestBase {

    /**
     * This test will print out all the request headers
     */
    @Test
    public void test001() {

        // logging header in request
        System.out.println("---------------Printing Request Headers------------------");
        given()
                .log().headers()
                .when()
                .get("/1")
                .then()
                .statusCode(200);
    }

    /**
     * This test will print out all the request Parameters
     */
    @Test
    public void test002() {
        System.out.println("---------------Printing Request Parameters------------------");

        given()
                .log().parameters()
                .param("firstName", "Vernon")
                .param("email", "abc@gmail.com")
                .when()
                .get("/list")
                .then()
                .statusCode(200);
    }

    /**
     * This test will print out the Request body
     */
    @Test
    public void test003() {
        System.out.println("---------------Printing Request Body------------------");

        List<String> course = new ArrayList<>();
        course.add("Selenium");
        course.add("Java");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("Roger");
        studentPojo.setLastName("Moore");
        studentPojo.setEmail("Roger"+getRandomString(4)+"@gmail.com");
        studentPojo.setProgramme("Automation Testing");
        studentPojo.setCourses(course);

        Response response = given()
                .contentType("application/json")
                .header("Content-Type", "application/json")
                .when()
                .log().body()
                .body(studentPojo)
                .post();
        response.then().statusCode(201);
    }

    /**
     * This test will print out All the details
     */
    @Test
    public void test004() {
        System.out.println("---------------Printing All the Request Details------------------");
        Response response = given()
                .log().all()
                .queryParam("programme", "Computer Science")
                .when()
                .get("/list");
        response.then().statusCode(200);
    }


    /**
     * This test will print Request details if validation fails
     */
    @Test
    public void test005() {
        System.out.println("---------------Printing All the Request Details if validation fails------------------");
        Response response = given()
                .log().ifValidationFails()
                .param("programme", "Financial Analysis")
                .param("limit", 2)
                .when()
                .get("/list");
        response.then().statusCode(500);


        //     response.prettyPrint();

    }
}
