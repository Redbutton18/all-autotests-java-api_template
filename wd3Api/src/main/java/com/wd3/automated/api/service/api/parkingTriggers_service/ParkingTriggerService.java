package com.wd3.automated.api.service.api.parkingTriggers_service;

import com.wd3.automated.api.AssertableResponse;
import com.wd3.automated.api.models.actionServiceModel.parkingTriggersModel.post_ParkingTringgerModel.PostParkingTriggerModel;
import com.wd3.automated.api.service.api.Wd3SetupApiService;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParkingTriggerService extends Wd3SetupApiService {

    @Step("Send POST  request")
    public AssertableResponse post_actionService(String token, PostParkingTriggerModel postParkingTriggerModel) {
        log.info("PostParkingTriggerModel {}", postParkingTriggerModel);
        Response register =
                baseSetupHeaders()
                        .header("Authorization", "Bearer " + token)
                        .body(postParkingTriggerModel)
                        .when()
                        .post("action-service")
                        .then().extract().response();
        return new AssertableResponse(register);
    }

    @Step("Send Delete action-service/parking/triggerId/{triggerId} request")
    public AssertableResponse delete_parkingTriggerById(String token, long triggerId) {
        log.info("Parking trigger {}", triggerId);
        Response register =
                baseSetupHeaders()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .delete("action-service/parking/triggerId/" + triggerId)
                        .then().extract().response();
        return new AssertableResponse(register);
    }

    @Step("Send Delete action-service/parking/triggerId/{triggerId} request Without Token")
    public AssertableResponse delete_parkingTriggerByIdWithoutToken(String token, long triggerId) {
        log.info("Parking trigger {}", triggerId);
        Response register =
                baseSetupHeaders()
                        .header("Authorization", "Bearer " + token)
                        .when()
                        .delete("action-service/parking/triggerId/" + triggerId)
                        .then().extract().response();
        return new AssertableResponse(register);
    }

}
