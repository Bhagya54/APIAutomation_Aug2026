package org.example.Ex003_CRUD_NonBDDStyle;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

//https://restful-booker.herokuapp.com/booking/1
public class TC013_GetBooking_NonBDDStyle {
    String id;
    RequestSpecification reqSpec;
    Response res;
    ValidatableResponse vRes;

    @BeforeTest
    public void setUp(){
        reqSpec = given();
        reqSpec.baseUri("https://restful-booker.herokuapp.com");
       }

    @Description("Positive Input: 1")
    @Test
    public void test_Get_Positive(){
        id="1";
        reqSpec.basePath("/booking/" + id);

        res=reqSpec.when().log().all().get();

        vRes=res.then();
        vRes.statusCode(200);
        vRes.log().all();
    }

    @Description("Negative Input: 0000")
    @Test
    public void test_Get_Negative1(){
        id="0000";
        reqSpec.basePath("/booking/" + id);

        res=reqSpec.when().log().all().get();

        vRes=res.then();
        vRes.statusCode(404);
        vRes.log().all();
    }
}
