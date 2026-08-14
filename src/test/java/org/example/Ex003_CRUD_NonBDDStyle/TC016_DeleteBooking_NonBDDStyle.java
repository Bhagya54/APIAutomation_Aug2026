package org.example.Ex003_CRUD_NonBDDStyle;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC016_DeleteBooking_NonBDDStyle
{
    RequestSpecification reqSpec;
    Response res;
    ValidatableResponse vRes;

    String payload;
    int bookingId = 3120;
    String token = "09571969cb4c54b";

    @BeforeMethod
    public void setUp() {
        reqSpec = given();
        reqSpec.baseUri("https://restful-booker.herokuapp.com");
        reqSpec.basePath("/booking/" + bookingId);
    }
    /* We need:
    token
    bookingId

    public String getToken(){}
    public String getBookingId(){}

    In this test we would hardcoad token and bookingId value. In future we call above 2 functions
     */
    @Description("Restful Booker-Delete Request-Delete Booking - Positive")
    @Test(priority = 3)
    public void deleteBooking_Positive() {

        //https://restful-booker.herokuapp.com/booking/123
        reqSpec.cookie("token",token);

        res = reqSpec.when().log().all().delete();

        vRes=res.then().log().all().statusCode(201);

    }

    @Description("Restful Booker-Delete Request-Delete Booking - Negative: Invalid token")
    @Test(priority = 4)
    public void deleteBooking_Negative1() {
        token="abc";

        //https://restful-booker.herokuapp.com/booking
        reqSpec.cookie("token",token);

        res = reqSpec.when().log().all().delete();

        vRes=res.then().log().all().statusCode(403);

    }


}
