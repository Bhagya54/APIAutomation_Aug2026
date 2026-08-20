package org.example.Ex005_PayloadManagement;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC021_Payload_Map {

    RequestSpecification reqSpec;
    Response response;
    ValidatableResponse vRes;
    Map<String,Object> payload;

    @BeforeTest
    public void setUp(){
        reqSpec = given();
        reqSpec.baseUri("https://restful-booker.herokuapp.com");
        reqSpec.basePath("/booking");
        payload=new LinkedHashMap<>();
    }



    @Description("Create Booking - Positive")
    @Test(priority=101)
    public void createBooking_Positive(){
        //Linked HashMap - maintains the order
        //HashMap

        payload.put("firstname","Jim");
        payload.put("lastname","Brown");
        payload.put("totalprice",345);
        payload.put("depositpaid",true);

        Map<String,Object> bookingDates=new LinkedHashMap<>();
        bookingDates.put("checkin","2018-01-01");
        bookingDates.put("checkout","2019-01-01");


        payload.put("bookingdates",bookingDates);
        payload.put("additionalneeds","breakfast");

        reqSpec.contentType("application/json");
        reqSpec.body(payload);
        reqSpec.log().all();

        response = reqSpec.when().post();

        vRes=response.then();
        vRes.log().all();
        vRes.body("bookingid", notNullValue());
        //vRes.body("booking['firstname']",equalTo("Jim"));
        vRes.body("booking.firstname",equalTo("Jim"));
        vRes.time(lessThan(5000L));
    }
}

