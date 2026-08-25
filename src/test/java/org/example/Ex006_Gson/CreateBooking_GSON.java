package org.example.Ex006_Gson;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.Ex005_PayloadManagement.pojos.Booking;
import org.example.Ex005_PayloadManagement.pojos.BookingDates;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.example.Ex006_Gson.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CreateBooking_GSON {
    //Send Request - Payload - serialization
    //http method - when - response
    //validation of Response

    RequestSpecification reqSpec;
    Response response;
    ValidatableResponse vRes;
    Booking booking;
    BookingDates bookingDates;
    BookingResponse bookingResponse;
    Gson gson = new Gson();

    @BeforeTest
    public void setUp() {
        reqSpec = given();
        reqSpec.baseUri("https://restful-booker.herokuapp.com");
        reqSpec.basePath("/booking");
        booking = new Booking();
        bookingDates = new BookingDates();
    }


    @Description("Create Booking - Positive")
    @Test(priority = 101)
    public void createBooking_Positive() {

        booking.setFirstname("Jim");
        booking.setLastname("Brown");
        booking.setTotalprice(234);
        booking.setDepositpaid(true);

        bookingDates.setCheckin("2018-01-01");
        bookingDates.setCheckout("2019-01-01");

        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("lunch");

        String jsonString = gson.toJson(booking);
        reqSpec.contentType("application/json");
        reqSpec.body(jsonString);
        reqSpec.log().all();

        response = reqSpec.when().post();

        vRes = response.then();
        vRes.log().all();

        //3 ways to extract the response body
        //1.extract() - path()

        int bookingId = vRes.extract().path("bookingid");
        System.out.println(bookingId);

        //2.JSONPath
        String responseString = response.asString();
        //System.out.println(responseString);
        JsonPath jsonPath = new JsonPath(responseString);
        int bookingid = jsonPath.getInt("bookingid");
        String fname = jsonPath.getString("booking.firstname");
        System.out.println("Booking Id: " + bookingid);
        System.out.println("First Name: " + fname);


        //3.Deserialization - from json string to java object
        bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        System.out.println(bookingResponse.getBookingid());
        System.out.println(bookingResponse.getBooking().getFirstname());
        System.out.println(bookingResponse.getBooking().getLastname());


        Assert.assertNotNull(bookingResponse.getBookingid());
        Assert.assertEquals(bookingResponse.getBooking().getFirstname(),"Jim");

    }
}
