package com.wd3.automated.actionService.parking;

import com.wd3.automated.api.models.actionServiceModel.parkingTriggersModel.post_ParkingTringgerModel.PostParkingTriggerModel;
import com.wd3.automated.api.service.api.parkingTriggers_service.ParkingTriggerService;
import com.wd3.automated.api.steps.authorization_steps.AuthorizationSteps;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.wd3.automated.api.conditions.Conditions.statusCode;
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
    public void testPostParkingTriggerEndPoint () {
        PostParkingTriggerModel postParkingTriggerModel = new PostParkingTriggerModel().randomize().setModuleName("parking");

        PostParkingTriggerModel postParkingTriggerResponseModel = parkingTriggerService
                .post_actionService(token, postParkingTriggerModel)
                .shouldHave(statusCode(200))
                .responseAs("result", PostParkingTriggerModel.class);

        assertEquals(postParkingTriggerModel.getTriggerId(), postParkingTriggerResponseModel.getTriggerId());
    }

}
