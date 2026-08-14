package org.example.Ex002_CRUD_BDDStyle;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC005_GetRequest_ZipCode {
    String zipCode;

    @Test
    public void zipCode_Positive() {
        zipCode = "100-0001";
        given()
                .baseUri("https://api.zippopotam.us/")
                .basePath("/JP/" + zipCode)
                .log().all()
        .when()
                .log().all()
                .get()
        .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void zipCode_Negative1() {
        zipCode = "-1";
        given()
                .baseUri("https://api.zippopotam.us/")
                .basePath("/JP/" + zipCode)
                .when()
                .log().all()
                .get()
                .then()
                .statusCode(404)
                .log().all();
    }

    @Test
    public void zipCode_Negative2() {
        zipCode = "abc";
        given()
                .baseUri("https://api.zippopotam.us/")
                .basePath("/JP/" + zipCode)
                .when()
                .log().all()
                .get()
                .then()
                .log().all()
                .statusCode(404);
    }
}
