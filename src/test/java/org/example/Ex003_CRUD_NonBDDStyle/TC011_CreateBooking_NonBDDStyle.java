package org.example.Ex003_CRUD_NonBDDStyle;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC011_CreateBooking_NonBDDStyle {
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


    }
    @Description("Create Booking - Negative - Wrong FirstName Body")
    @Test(priority = 200)
    public void createBooking_Negative(){
        String payload = "{\n" +
                "    \"firstname\" : 12344,\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"breakfast\"\n" +
                "}";
        reqSpec.contentType("application/json");
        reqSpec.body(payload);
        reqSpec.log().all();

        response = reqSpec.when().post();

        vRes=response.then();
        vRes.log().all();
        vRes.statusCode(500);
    }


    @Description("Create Booking - Negative - Header Content Type Not Mentioned")
    @Test(priority = 300)
    public void createBooking_Negative2(){
        String payload = "{\n" +
                "    \"firstname\" : 12344,\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"breakfast\"\n" +
                "}";

        reqSpec.body(payload);
        reqSpec.log().all();

        response = reqSpec.when().post();

        vRes=response.then();
        vRes.log().all();
        vRes.statusCode(500);
    }

}
