package org.example.Ex003_CRUD_NonBDDStyle;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TC010_CreateToken_NonBDDStyle {
    //https://restful-booker.herokuapp.com/auth
    //'Content-Type: application/json'
    /*
    {
    "username" : "admin",
    "password" : "password123"
    }
     */

    RequestSpecification reqSpec;
    Response res;
    ValidatableResponse vRes;

    @BeforeTest
    public void setUp(){
        reqSpec= given();
        reqSpec.baseUri("https://restful-booker.herokuapp.com");
        reqSpec.basePath("/auth");
        //.header("Content-Type","application/json")
        reqSpec.contentType(ContentType.JSON);
    }


    @Description("TC#01_Token Generation_Positive")
    @Test
    public void createToken(){
        String requestPayload="{\n" +
                "    \"username\": \"admin\",\n" +
                "    \"password\": \"password123\"\n" +
                "}";

        reqSpec.body(requestPayload);
        reqSpec.log().all();


        res=reqSpec.when().post();


        vRes=res.then();
        vRes.log().all();
        vRes.statusCode(200);


    }


    @Description("TC#02_Token Generation_Negative")
    @Test
    public void createToken_Negative(){
        String requestPayload="{\n" +
                "    \"username\": \"admin\",\n" +
                "    \"password\": \"gdfgfg\"\n" +
                "}";

        reqSpec.body(requestPayload);
        reqSpec.log().all();

        res=reqSpec.when().post();

        vRes=res.then();
        vRes.log().all();
        vRes.statusCode(200);


    }
}
