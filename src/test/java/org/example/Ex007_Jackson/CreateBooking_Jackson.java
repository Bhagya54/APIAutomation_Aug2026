package org.example.Ex007_Jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateBooking_Jackson {
    //Request - payload - jsonString
    //Booking java object - > jsonString - Serialization
    //ObjectMapper - writeValueAsString(javaObj)
    ObjectMapper objectMapper = new ObjectMapper();
    Booking booking;
    BookingDates bookingDates;
    BookingResponse bookingResponse;
    RequestSpecification reqSpec;
    Response response;
    ValidatableResponse vRes;

    @BeforeTest
    public void setUp(){
        booking = new Booking();
        bookingDates = new BookingDates();
        reqSpec=given();
        reqSpec.baseUri("https://restful-booker.herokuapp.com");
        reqSpec.basePath("/booking");
        reqSpec.contentType(ContentType.JSON);
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

        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(booking);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        reqSpec.body(jsonString);
        reqSpec.log().all();

        //HTTP Method - POST
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
        //Extract Response - Deserization - obj.readValue(responseString,class)
        try {
            bookingResponse = objectMapper.readValue(responseString,BookingResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(bookingResponse.getBookingid());
        System.out.println(bookingResponse.getBooking().getFirstname());
        System.out.println(bookingResponse.getBooking().getLastname());


        Assert.assertNotNull(bookingResponse.getBookingid());
        Assert.assertEquals(bookingResponse.getBooking().getFirstname(),"Jim");

    }




}
