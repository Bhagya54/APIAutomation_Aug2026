package org.example.Ex002_CRUD_BDDStyle;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC007_CreateBooking {
    //https://restful-booker.herokuapp.com/booking
    //'Content-Type: application/json'
    /*
    {
    "firstname" : "Jim",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}
     */

    @Description("TC#01_Create Booking_Positive")
    @Test
    public void createBooking_Positive(){
        String requestPayload="{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .contentType(ContentType.JSON)
                .body(requestPayload)
        .when()
                .log().all()
                .post()
        .then()
                .log().all()
                .statusCode(200);
    }


    @Description("TC#02_Create Booking_Negative_No Content Type")
    @Test
    public void createBooking_Negative(){
        String requestPayload="{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .body(requestPayload)
        .when()
                .log().all()
                .post()
        .then()
                .log().all()
                .statusCode(500);
    }
}
