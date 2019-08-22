package com.wd3.automated.actionService.parking;

import com.wd3.automated.api.models.actionServiceModel.parkingTriggersModel.post_ParkingTringgerModel.PostParkingTriggerModel;
import com.wd3.automated.api.service.api.parkingTriggers_service.ParkingTriggerService;
import com.wd3.automated.api.steps.authorization_steps.AuthorizationSteps;
import com.wd3.automated.api.steps.parking_steps.trigger.ParkingTriggerSteps;
import io.qameta.allure.TmsLink;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.wd3.automated.api.conditions.Conditions.bodyField;
import static com.wd3.automated.api.conditions.Conditions.statusCode;
import static com.wd3.automated.api.utilits.dataGenerator.UserDataGenerator.getFakerRandomNumberInt;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class Delete_ParkingTriggerTest {

    private String token = "";

    private ParkingTriggerService parkingTriggerService = new ParkingTriggerService();

    private AuthorizationSteps authorizationSteps = new AuthorizationSteps();
    private ParkingTriggerSteps parkingTriggerSteps = new ParkingTriggerSteps();

    private List<PostParkingTriggerModel> postParkingTriggerModelList = new ArrayList<>();

    @BeforeClass
    private void preConditions() {
        token = authorizationSteps.authorizationBySuperUser_returnAuthToken();
    }

    @TmsLink(value = "65981")
    @Test(description = "Delete Parking Trigger Without Token")
    public void testDeleteParkingTriggerWithoutToken() {
        PostParkingTriggerModel postParkingTriggerResponseModel = parkingTriggerSteps.createDefaultParkingTrigger_returnPostParkingTriggerModel(token);
        postParkingTriggerModelList.add(postParkingTriggerResponseModel);

        parkingTriggerService
                .delete_parkingTriggerByIdWithoutToken("", postParkingTriggerResponseModel.getTriggerId())
                .shouldHave(statusCode(401),
                        bodyField("result.error", containsString("Authentication Required.")));

    }

    @TmsLink(value = "65979")
    @Test(description = "Delete Parking Trigger ")
    public void testDeleteParkingTriggerEndPoint() {
        PostParkingTriggerModel postParkingTriggerResponseModel = parkingTriggerSteps.createDefaultParkingTrigger_returnPostParkingTriggerModel(token);

        parkingTriggerService.delete_parkingTriggerById(token, postParkingTriggerResponseModel.getTriggerId())
                .shouldHave(statusCode(200),
                        bodyField("result.triggerId", equalTo(postParkingTriggerResponseModel.getTriggerId())));
    }

    @TmsLink(value = "65982")
    @Test(description = "Delete Parking Trigger With Non Exist ID")
    public void testDeleteParkingTriggerWithNonExistID() {
        parkingTriggerService
                .delete_parkingTriggerById(token, getFakerRandomNumberInt(5))
                .shouldHave(statusCode(400),
                        bodyField("result.error", containsString("Trigger Id Does Not Exist")));
    }

    @AfterClass
    public void afterClass() {
        for (PostParkingTriggerModel postParkingTriggerModel : postParkingTriggerModelList) {
            parkingTriggerSteps.deleteParkingTriggerByTriggerId(token, postParkingTriggerModel.getTriggerId());
        }
    }


}
