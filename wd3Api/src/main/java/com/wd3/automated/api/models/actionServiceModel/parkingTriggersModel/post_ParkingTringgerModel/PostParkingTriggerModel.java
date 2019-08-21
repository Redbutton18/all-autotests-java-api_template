package com.wd3.automated.api.models.actionServiceModel.parkingTriggersModel.post_ParkingTringgerModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.annotation.Generated;

import static com.wd3.automated.api.utilits.dataGenerator.UserDataGenerator.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class PostParkingTriggerModel{

	@JsonProperty("triggerId")
	private int triggerId;

	@JsonProperty("moduleName")
	private String moduleName;

	@JsonProperty("conditionParamValue")
	private ConditionParamValue conditionParamValue;

	@JsonProperty("activeAlertType")
	private int activeAlertType;

	//needed field in response
	@JsonProperty("activeSourceType")
	private String activeSourceType;

	@JsonProperty("activeSource")
	private String activeSource;




	public PostParkingTriggerModel randomize(){
		this.moduleName = "parking";
		this.triggerId = getFakerRandomNumberInt(7);
		this.activeAlertType = 103;
		this.conditionParamValue = new ConditionParamValue().randomize();
		return this;
	}
}