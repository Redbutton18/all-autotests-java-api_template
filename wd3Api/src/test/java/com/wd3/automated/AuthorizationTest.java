package com.wd3.automated;

import com.wd3.automated.api.models.authorizationModel.request.AuthorizationModel;
import com.wd3.automated.api.models.authorizationModel.response.AuthorizationResponseModel;
import com.wd3.automated.api.service.api.authorization_service.AuthorizationService;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

import static com.wd3.automated.api.conditions.Conditions.bodyField;
import static com.wd3.automated.api.conditions.Conditions.statusCode;
import static com.wd3.automated.api.properties.UserProp.USER_NAME_LOGIN;
import static com.wd3.automated.api.properties.UserProp.USER_PASSWORD;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class AuthorizationTest {

    private AuthorizationService authorizationService = new AuthorizationService();

    @TmsLink(value = "64231")
    @Test(description = "Generate token for authorization")
    public void testGenerateAuthorizationTokenVersion1Example() {
        AuthorizationModel authorizationModel = new AuthorizationModel()
                .setLogin(USER_NAME_LOGIN)
                .setPassword(USER_PASSWORD);

        AuthorizationResponseModel authorizationResponseModel = authorizationService.authorization(authorizationModel)
                .shouldHave(statusCode(200))
                .responseAs(AuthorizationResponseModel.class);

        assertEquals(authorizationResponseModel.getResult().getMessage(),"Logged in successfully.");
        assertNotNull(authorizationResponseModel.getResult().getToken(), "Token is null");
    }

    @TmsLink(value = "64231")
    @Test(description = "Generate token for authorization")
    public void testGenerateAuthorizationTokenVersion2Example() {
        AuthorizationModel authorizationModel = new AuthorizationModel()
                .setLogin(USER_NAME_LOGIN)
                .setPassword(USER_PASSWORD);

                authorizationService.authorization(authorizationModel)
                .shouldHave((statusCode(200)),
                        bodyField("result.message", containsString("Logged in successfully.")),
                        bodyField("result.token", not(empty())));
    }

}
