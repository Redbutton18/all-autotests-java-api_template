package com.wd3.automated.api.steps.parking_steps.trigger;

import com.wd3.automated.api.models.actionServiceModel.parkingTriggersModel.post_ParkingTringgerModel.PostParkingTriggerModel;
import com.wd3.automated.api.service.api.parkingTriggers_service.ParkingTriggerService;
import io.qameta.allure.Step;

import static com.wd3.automated.api.conditions.Conditions.statusCode;

public class ParkingTriggerSteps {

    private ParkingTriggerService parkingTriggerService = new ParkingTriggerService();

    @Step("Create default parking trigger and return PostParkingTriggerModel")
    public PostParkingTriggerModel createDefaultParkingTrigger_returnPostParkingTriggerModel(String authToken) {
        PostParkingTriggerModel postParkingTriggerModel = new PostParkingTriggerModel().randomize().setModuleName("parking");

        PostParkingTriggerModel postParkingTriggerResponseModel = parkingTriggerService
                .post_actionService(authToken, postParkingTriggerModel)
                .shouldHave(statusCode(200))
                .responseAs( "result", PostParkingTriggerModel.class);

        return postParkingTriggerResponseModel;
    }

}
