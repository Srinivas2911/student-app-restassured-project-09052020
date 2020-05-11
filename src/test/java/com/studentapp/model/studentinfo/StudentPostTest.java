package com.studentapp.model.studentinfo;

/*
Created by SP
*/

import com.studentapp.model.StudentPojo;
import com.studentapp.model.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StudentPostTest extends TestBase {

    @Test
    public void createNewStudent() {

        List<String> courses = new ArrayList<>();
        courses.add("Acting");
        courses.add("Photography");
        courses.add("Filming");
        courses.add("Direction");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("Roger");
        studentPojo.setLastName("Moore");
        studentPojo.setEmail("Roger" + getRandomString(4) + "@gmail.com");
        studentPojo.setProgramme("Cinematography");
        studentPojo.setCourses(courses);

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .log().body()
                .body(studentPojo)
                .post();
        response.then().statusCode(201);
        response.prettyPrint();
    }

    @Test
    public void createNewStudentWithNullFirstName() {

        List<String> courses = new ArrayList<>();
        courses.add("Acting");
        courses.add("Filming");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(" ");
        studentPojo.setLastName("Moore");
        studentPojo.setEmail("Roger" + getRandomString(4) + "@gmail.com");
        studentPojo.setProgramme("Cinematography");
        studentPojo.setCourses(courses);

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .log().body()
                .body(studentPojo)
                .post();
        response.then().statusCode(201)
        .log().ifStatusCodeIsEqualTo(201);
              response.prettyPrint();
    }

    @Test
    public void createNewStudentWithNullLastName() {

        List<String> courses = new ArrayList<>();
        courses.add("Acting");
        courses.add("Filming");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("Roger");
        studentPojo.setLastName(" ");
        studentPojo.setEmail("Roger" + getRandomString(4) + "@gmail.com");
        studentPojo.setProgramme("Cinematography");
        studentPojo.setCourses(courses);

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .log().body()
                .body(studentPojo)
                .post();
        response.then().statusCode(201)
                .log().ifValidationFails();
        response.prettyPrint();
    }

    @Test
    public void createNewStudentWithDuplicateEmailId() {

        List<String> courses = new ArrayList<>();
        courses.add("Acting");
        courses.add("Filming");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("Roger");
        studentPojo.setLastName(" ");
        studentPojo.setEmail("tincidunt.dui@ultricessit.co.uk");
        studentPojo.setProgramme("Cinematography");
        studentPojo.setCourses(courses);

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .log().body()
                .body(studentPojo)
                .post();
        response.then().statusCode(500).log().all();
        response.prettyPrint();
    }
}



