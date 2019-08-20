package com.wd3.automated.api.service.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static com.wd3.automated.api.properties.UrlLinks.MAIN_URL;


public class Wd3SetupApiService {

    public RequestSpecification baseSetupHeaders(){
        return RestAssured.given()
                .baseUri(MAIN_URL)
                .contentType(ContentType.JSON)
                .header("X-Requested-With", "XMLHttpRequest")
                .filters(new RequestLoggingFilter(),
                        new ResponseLoggingFilter(),
                        new AllureRestAssured());
    }
}
