package org.example.Ex005_PayloadManagement;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
public class TC020_Payload_String {

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
    }
}

