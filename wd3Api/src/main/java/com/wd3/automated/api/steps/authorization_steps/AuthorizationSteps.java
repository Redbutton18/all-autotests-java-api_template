package com.wd3.automated.api.steps.authorization_steps;

import com.wd3.automated.api.models.authorizationModel.request.AuthorizationModel;
import com.wd3.automated.api.service.api.authorization_service.AuthorizationService;
import io.qameta.allure.Step;

import static com.wd3.automated.api.conditions.Conditions.statusCode;
import static com.wd3.automated.api.properties.UserProp.USER_NAME_LOGIN;
import static com.wd3.automated.api.properties.UserProp.USER_PASSWORD;

public class AuthorizationSteps {

    private AuthorizationService authorizationService = new AuthorizationService();

    @Step("Authorization by SuperUser return AuthToken")
    public String authorizationBySuperUser_returnAuthToken() {
        AuthorizationModel authorizationModel = new AuthorizationModel()
                .setLogin(USER_NAME_LOGIN)
                .setPassword(USER_PASSWORD);

        String authToken = authorizationService.authorization(authorizationModel)
                .shouldHave((statusCode(200)))
                .getBodyByPath("result.token");

        return authToken;
    }

    @Step("Authorization by user credential return AuthToken")
    public String authorizationByUserCred_returnAuthToken(String userName, String password) {
        AuthorizationModel authorizationModel = new AuthorizationModel()
                .setLogin(userName)
                .setPassword(password);

        String authToken = authorizationService.authorization(authorizationModel)
                .shouldHave((statusCode(200)))
                .getBodyByPath("result.token");

        return authToken;
    }

}
