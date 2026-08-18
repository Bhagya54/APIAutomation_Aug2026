package org.example.Ex004_Assertions;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC017_Assertion_RestAssured_Hamcrest {
/*
https://restful-booker.herokuapp.com/booking
-H 'Content-Type: application/json' \
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
    RequestSpecification reqSpec;
    Response response;
    ValidatableResponse vRes;


    @BeforeTest
    public void setUp(){
        reqSpec = given();
        reqSpec.baseUri("https://restful-booker.herokuapp.com");
        reqSpec.basePath("/booking");

    }

    @Description("Create Booking - Positive")
    @Test(priority=101)
    public void createBooking_Positive(){
        String payload = "{\n" +
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
        reqSpec.contentType("application/json");
        reqSpec.body(payload);
        reqSpec.log().all();

        response = reqSpec.when().post();

        vRes=response.then();
        vRes.log().all();
        vRes.statusCode(200);
        vRes.header("Content-Type","application/json; charset=utf-8");
        vRes.body("booking.firstname",equalTo("Jim"));
        vRes.body("booking.lastname",equalTo("Brown"));
        vRes.body("booking.bookingdates.checkin",equalTo("2018-01-01"));
        vRes.time(lessThan(3000L));
        vRes.body("bookingid",notNullValue());
       // vRes.cookie("","");
    }


}
