package org.example.Ex002_CRUD_BDDStyle;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC009_DeleteBooking {

    //https://restful-booker.herokuapp.com/booking/1
    //Content-Type: application/json
    //'Cookie: token=abc123'

    @Test
    public void DeleteBooking(){

        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/1737")
                .contentType(ContentType.JSON)
                .cookie("token","080d2fe9f05be3a")

        .when()
                .log().all()
                .delete()
        .then()
                .log().all()
                .statusCode(201);
    }
}
