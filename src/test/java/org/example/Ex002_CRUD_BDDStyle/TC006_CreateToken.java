package org.example.Ex002_CRUD_BDDStyle;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
public class TC006_CreateToken {
    //https://restful-booker.herokuapp.com/auth
    //'Content-Type: application/json'
    /*
    {
    "username" : "admin",
    "password" : "password123"
    }
     */

    @Description("TC#01_Token Generation_Positive")
    @Test
    public void createToken(){
        String requestPayload="{\n" +
                "    \"username\": \"admin\",\n" +
                "    \"password\": \"password123\"\n" +
                "}";
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                //.header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .body(requestPayload)
        .when()
                .log().all()
                .post()
        .then()
                .log().all()
                .statusCode(200);


    }


    @Description("TC#02_Token Generation_Negative")
    @Test
    public void createToken_Negative(){
        String requestPayload="{\n" +
                "    \"username\": \"admin\",\n" +
                "    \"password\": \"gdfgfg\"\n" +
                "}";
        given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                //.header("Content-Type","application/json")
                .contentType(ContentType.JSON)
                .body(requestPayload)
                .when()
                .log().all()
                .post()
                .then()
                .log().all()
                .statusCode(200);
    }
}
