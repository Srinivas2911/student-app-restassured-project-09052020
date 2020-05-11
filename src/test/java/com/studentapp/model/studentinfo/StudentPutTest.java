package com.studentapp.model.studentinfo;

/*
Created by SP
*/

import com.studentapp.model.StudentPojo;
import com.studentapp.model.testbase.TestBase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class StudentPutTest extends TestBase {

    @Test
    public void upDateStudentInfoById() {

        List<String> courses = new ArrayList<>();
        courses.add("Calculus");
        courses.add("Algorithms");
        courses.add("Software Development");
        courses.add("Ethics");


        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("Johny");
        studentPojo.setLastName("Cash");
        studentPojo.setEmail("JohnyCash1234@gmail.com");
        studentPojo.setProgramme("Computer Science");
        studentPojo.setCourses(courses);

        given()
                .header("Content-Type", "application/json")
                .when()
                .log().body()
                .body(studentPojo)
                .put("/4")
                .then()
                .statusCode(200).log().all();
        get("/list")
                .then()
                .body("[3].id", equalTo(4), "[3].firstName", equalTo("Johny"),
                        "[3].lastName", equalTo("Cash"),
                        "[3].email", equalTo("JohnyCash1234@gmail.com"));

    }

    @Test
    public void upDateStudentEmailById() {

        List<String> courses = new ArrayList<>();
        courses.add("Machine Element Design");
        courses.add("Engineering Analysis I");
        courses.add("Calculus & Linear Algebra I ");

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName("Omar");
        studentPojo.setLastName("Colt");
        studentPojo.setEmail("Omar.Colt@Pellentesque.co.uk");
        studentPojo.setProgramme("Mechanical Engineering");
        studentPojo.setCourses(courses);

        given()
                .header("Content-Type", "application/json")
                .when().log().body()
                .body(studentPojo)
                .put("/45")
                .then()
                .statusCode(200).log().all()
                .body("msg", equalTo("Student Updated"));

    }
}
