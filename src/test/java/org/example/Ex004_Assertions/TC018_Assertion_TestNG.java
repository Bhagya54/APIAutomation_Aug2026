package org.example.Ex004_Assertions;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC018_Assertion_TestNG {
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

        //extract
        int bookingId=vRes.extract().path("bookingid");
        String firstName=vRes.extract().path("booking.firstname");
        String lastName=vRes.extract().path("booking.lastname");
        String checkout=vRes.extract().path("booking.bookingdates.checkout");
        String responseHeader=vRes.extract().contentType();
       // String cookieName=vRes.extract().cookie("");
        Long responseTime=vRes.extract().time();
        System.out.println(responseTime);


        //TestNG- Hard Assert
        Assert.assertNotNull(bookingId);
        Assert.assertEquals(firstName,"Jim");
        Assert.assertTrue(lastName.contains("Bro"));
        Assert.assertEquals(checkout,"2019-01-01");

        //Assert.assertTrue(headerContentType.contains("application/json"));
        Assert.assertEquals(responseHeader,"application/json; charset=utf-8");
        //Assert.assertTrue(responseTime.lessThan(5000));






    }


}
