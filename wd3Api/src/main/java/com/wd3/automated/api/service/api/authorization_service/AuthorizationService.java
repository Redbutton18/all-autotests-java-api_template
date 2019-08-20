package com.wd3.automated.api.service.api.authorization_service;

import com.wd3.automated.api.AssertableResponse;
import com.wd3.automated.api.models.authorizationModel.request.AuthorizationModel;
import com.wd3.automated.api.service.api.Wd3SetupApiService;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthorizationService extends Wd3SetupApiService {

    @Step("Send POST  request")
    public AssertableResponse authorization(AuthorizationModel authorizationModel) {
        log.info("Authorization {}", authorizationModel);
        Response register =
                baseSetupHeaders()
                        .body(authorizationModel)
                        .when()
                        .post("")
                        .then().extract().response();
        return new AssertableResponse(register);
    }

}
