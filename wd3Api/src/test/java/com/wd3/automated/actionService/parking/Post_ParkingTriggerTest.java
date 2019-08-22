package com.wd3.automated.actionService.parking;

import com.wd3.automated.api.models.actionServiceModel.parkingTriggersModel.post_ParkingTringgerModel.PostParkingTriggerModel;
import com.wd3.automated.api.service.api.parkingTriggers_service.ParkingTriggerService;
import com.wd3.automated.api.steps.authorization_steps.AuthorizationSteps;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.wd3.automated.api.conditions.Conditions.bodyField;
import static com.wd3.automated.api.conditions.Conditions.statusCode;
import static org.hamcrest.Matchers.containsString;
import static org.testng.Assert.assertEquals;

public class Post_ParkingTriggerTest {

    private String token = "";

    private ParkingTriggerService parkingTriggerService = new ParkingTriggerService();

    private AuthorizationSteps authorizationSteps = new AuthorizationSteps();

    @BeforeClass
    private void preConditions() {
        token = authorizationSteps.authorizationBySuperUser_returnAuthToken();
    }


    @TmsLink(value = "65953")
    @Test(description = "Post Parking Trigger ")
    public void testPostParkingTriggerEndPoint() {
        PostParkingTriggerModel postParkingTriggerModel = new PostParkingTriggerModel().randomize().setModuleName("parking");

        PostParkingTriggerModel postParkingTriggerResponseModel = parkingTriggerService
                .post_actionService(token, postParkingTriggerModel)
                .shouldHave(statusCode(200))
                .responseAs("result", PostParkingTriggerModel.class);

        assertEquals(postParkingTriggerModel.getTriggerId(), postParkingTriggerResponseModel.getTriggerId());
        assertEquals(postParkingTriggerModel.getModuleName(), postParkingTriggerResponseModel.getModuleName());
        assertEquals(postParkingTriggerModel.getActiveAlertType(), postParkingTriggerResponseModel.getActiveAlertType());
        assertEquals(postParkingTriggerModel.getActiveSourceType(), postParkingTriggerResponseModel.getActiveSourceType());
        assertEquals(postParkingTriggerModel.getConditionParamValue().toString(), postParkingTriggerResponseModel.getConditionParamValue().toString());

    }

    @TmsLink(value = "65963")
    @Test(description = "Post Parking Trigger Without Authorization Token ")
    public void testPostParkingTriggerWithoutAuthorizationToken() {
        PostParkingTriggerModel postParkingTriggerModel = new PostParkingTriggerModel().randomize().setModuleName("parking");

        parkingTriggerService
                .post_actionService("", postParkingTriggerModel)
                .shouldHave(statusCode(401),
                        bodyField("result.error", containsString("Authentication Required.")));

    }


}
