package org.example.Ex002_CRUD_BDDStyle;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC008_GetBooking {
    //https://restful-booker.herokuapp.com/booking/:id
    //
    //



    @Test
    public void GetBooking(){

        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/4542")


        .when()
                .log().all()
                .get()
        .then()
                .log().all()
                .statusCode(200);
    }
}
