package org.example.Ex002_CRUD_BDDStyle;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC009_UpdateBooking {
    //https://restful-booker.herokuapp.com/booking/:id
    //'Content-Type: application/json'
    //'Cookie: token=abc123'
    /*
    {
    "firstname" : "James",
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

    @Test
    public void UpdateBooking(){
        String requestPayload="{\n" +
                "    \"firstname\" : \"James\",\n" +
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
                .basePath("/booking/1490")
                //.header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .cookie("token","07e6a923720c3ba")
                .body(requestPayload)
        .when()
                .log().all()
                .put()
        .then()
                .log().all()
                .statusCode(200);
    }
}
