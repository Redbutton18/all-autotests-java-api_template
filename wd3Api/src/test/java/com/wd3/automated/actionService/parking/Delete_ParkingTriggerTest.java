package com.wd3.automated.actionService.parking;

import com.wd3.automated.api.models.actionServiceModel.parkingTriggersModel.post_ParkingTringgerModel.PostParkingTriggerModel;
import com.wd3.automated.api.service.api.parkingTriggers_service.ParkingTriggerService;
import com.wd3.automated.api.steps.authorization_steps.AuthorizationSteps;
import com.wd3.automated.api.steps.parking_steps.trigger.ParkingTriggerSteps;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.wd3.automated.api.conditions.Conditions.bodyField;
import static com.wd3.automated.api.conditions.Conditions.statusCode;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertEquals;

public class Delete_ParkingTriggerTest {

    private String token = "";

    private ParkingTriggerService parkingTriggerService = new ParkingTriggerService();

    private AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    private ParkingTriggerSteps parkingTriggerSteps = new ParkingTriggerSteps();


    @BeforeClass
    private void preConditions() {
        token = authorizationSteps.authorizationBySuperUser_returnAuthToken();
    }


    @TmsLink(value = "65979")
    @Test(description = "Post Parking Trigger ")
    public void testDeleteParkingTriggerEndPoint () {
        PostParkingTriggerModel postParkingTriggerResponseModel = parkingTriggerSteps.createDefaultParkingTrigger_returnPostParkingTriggerModel(token);

        parkingTriggerService.delete_parkingTriggerById(token, postParkingTriggerResponseModel.getTriggerId())
            .shouldHave(statusCode(200),
                    bodyField("result.triggerId", equalTo(postParkingTriggerResponseModel.getTriggerId())));
    }

}
