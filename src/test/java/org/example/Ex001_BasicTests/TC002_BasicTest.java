package org.example.Ex001_BasicTests;

import static io.restassured.RestAssured.given;

public class TC002_BasicTest {
    static String zipCode;

    public static void main(String[] args) {
        zipCode = "110001";
        given()
                .baseUri("https://api.zippopotam.us/")
                .basePath("IN/" + zipCode)

                .when()
                .log().all()
                .get()
                .then()
                .log().all()
                .statusCode(200);

        zipCode="-1";
        given()
                .baseUri("https://api.zippopotam.us/")
                .basePath("IN/" + zipCode)

                .when()
                .log().all()
                .get()
                .then()
                .log().all()
                .statusCode(404);
    }
}
