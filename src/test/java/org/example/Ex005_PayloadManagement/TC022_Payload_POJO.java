package org.example.Ex005_PayloadManagement;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.Ex005_PayloadManagement.pojos.Booking;
import org.example.Ex005_PayloadManagement.pojos.BookingDates;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TC022_Payload_POJO {

    RequestSpecification reqSpec;
    Response response;
    ValidatableResponse vRes;
    Booking booking;
    BookingDates bookingDates;
    @BeforeTest
    public void setUp(){
        reqSpec = given();
        reqSpec.baseUri("https://restful-booker.herokuapp.com");
        reqSpec.basePath("/booking");
        booking=new Booking();
        bookingDates=new BookingDates();
    }



    @Description("Create Booking - Positive")
    @Test(priority=101)
    public void createBooking_Positive(){

        booking.setFirstname("Jim");
        booking.setLastname("Brown");
        booking.setTotalprice(234);
        booking.setDepositpaid(true);

        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");

        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("lunch");

        reqSpec.contentType("application/json");
        reqSpec.body(booking);
        reqSpec.log().all();

        response = reqSpec.when().post();

        vRes=response.then();
        vRes.log().all();
        vRes.body("bookingid", notNullValue());
        //vRes.body("booking['firstname']",equalTo("Jim"));
        vRes.body("booking.firstname",equalTo("jim"));
        vRes.time(lessThan(5000L));
    }
}

