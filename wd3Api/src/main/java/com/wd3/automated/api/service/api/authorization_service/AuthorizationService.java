package com.wd3.automated.api.service.api.authorization_service;

import com.wd3.automated.api.AssertableResponse;
import com.wd3.automated.api.models.authorizationModel.AuthorizationModel;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class AuthorizationService {

    @Step("Send POST v1/auth/dologin request")
    public AssertableResponse authorization(AuthorizationModel authorizationModel, String... apiKey) {
//        log.info("doLogin User {}", authorizationModel);
//        Response register =
//                setXApiKeyHHeader(apiKey)
//                        .body(doLoginModel)
//                        .header("Accept-Language", lang)
//                        .when()
//                        .post("auth/dologin")
//                        .then().extract().response();
//        return new AssertableResponse(register);
        return null;
    }

}
