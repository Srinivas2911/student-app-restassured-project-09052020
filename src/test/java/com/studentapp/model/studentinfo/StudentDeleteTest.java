package com.studentapp.model.studentinfo;

/*
Created by SP
*/

import com.studentapp.model.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentDeleteTest extends TestBase {

    @Test
    public void deleteARecordbyID() {
        Response response = given()
                .when()
                .delete("/114");
        response.then().statusCode(204).log().status();
        response.prettyPrint();
    }


}
