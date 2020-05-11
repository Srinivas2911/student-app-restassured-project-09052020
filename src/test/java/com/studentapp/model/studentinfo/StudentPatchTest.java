package com.studentapp.model.studentinfo;

/*
Created by SP
*/

import com.studentapp.model.StudentPojo;
import com.studentapp.model.testbase.TestBase;
import org.junit.Test;


import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StudentPatchTest extends TestBase {

    String email = "Boris" + getRandomString(4) + "@Johnson.co.uk";

    @Test
    public void patchUpStudentEmailById() {

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setEmail(email);

        given()
                .header("Content-Type", "application/json")
                .when()
                .log().body()
                .body(studentPojo)
                .patch("/5")
                .then()
                .log().all()
                .statusCode(200)
                .body("msg", equalTo("Updated"));
    }

}
