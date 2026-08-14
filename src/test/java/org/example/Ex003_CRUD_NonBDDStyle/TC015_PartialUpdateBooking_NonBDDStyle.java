package org.example.Ex003_CRUD_NonBDDStyle;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC015_PartialUpdateBooking_NonBDDStyle {
    RequestSpecification reqSpec;
    Response res;
    ValidatableResponse vRes;

    String payload;
    int bookingId = 1;
    String token = "2c8a5596f7c5124";

    @BeforeMethod
    public void setUp() {
        reqSpec = given();
        reqSpec.baseUri("https://restful-booker.herokuapp.com");
        reqSpec.contentType(ContentType.JSON);
        reqSpec.basePath("/booking/" + bookingId);
    }

    /* We need:
    token
    bookingId

    public String getToken(){}
    public String getBookingId(){}

    In this test we would hardcoad token and bookingId value. In future we call above 2 functions
     */
    @Description("Restful Booker-Patch Request-Update Booking - Positive")
    @Test(priority = 3)
    public void partialUpdateBooking_Positive() {
        payload = "{\n" +
                "    \"firstname\" : \"Sonia\",\n" +
                "    \"lastname\" : \"chary\"\n" +
                "}";
        //https://restful-booker.herokuapp.com/booking
        reqSpec.body(payload);
        reqSpec.cookie("token", token);

        res = reqSpec.when().log().all().patch();

        vRes = res.then().log().all().statusCode(200);

    }

    @Description("Restful Booker-Put Request-Update Booking - Negative: Invalid token")
    @Test(priority = 4)
    public void updateBooking_Negative1() {
        token = "abc";
        payload = "{\n" +
                "    \"firstname\" : \"Sonia\",\n" +
                "    \"lastname\" : \"chary\"\n" +
                "}";
        //https://restful-booker.herokuapp.com/booking
        reqSpec.body(payload);
        reqSpec.cookie("token", token);

        res = reqSpec.when().log().all().patch();

        vRes = res.then().log().all().statusCode(403);

    }


}
