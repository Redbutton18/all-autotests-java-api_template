package com.wd3.automated;

import com.wd3.automated.api.models.authorizationModel.AuthorizationModel;
import com.wd3.automated.api.service.api.authorization_service.AuthorizationService;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.wd3.automated.api.conditions.Conditions.bodyField;
import static com.wd3.automated.api.conditions.Conditions.statusCode;
import static org.hamcrest.Matchers.*;

public class TestInit {

    private AuthorizationService authorizationService = new AuthorizationService();

    @TmsLink(value = "64231")
    @Test(description = "Generate token for authorization")
    public void doLoginOperatorCorrectData() {
        AuthorizationModel authorizationModel = new AuthorizationModel()
                .setLogin("AntonWD3")
                .setPassword("QAZ123");

        authorizationService.authorization(authorizationModel)
                .shouldHave((statusCode(201)),
                        bodyField("loggedIn", is(true)),
                        bodyField("token", not(empty())),
                        bodyField("userId", not(empty())));
    }

//    @TmsLink(value = "64233")
//    @Test(description = "Login Operator with correct data")
//    public void doLoginOperatorCorrectData() {
//    }

}
