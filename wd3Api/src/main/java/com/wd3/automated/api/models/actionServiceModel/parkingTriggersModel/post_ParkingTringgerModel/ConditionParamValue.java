package com.wd3.automated.api.models.actionServiceModel.parkingTriggersModel.post_ParkingTringgerModel;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.annotation.Generated;

import static com.wd3.automated.api.utilits.dataGenerator.DateGenerator.getCurrentDate;
import static com.wd3.automated.api.utilits.dataGenerator.UserDataGenerator.getFakerRandomNumberInt;
import static com.wd3.automated.api.utilits.dataGenerator.UserDataGenerator.getFakerUserName;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConditionParamValue{

	@JsonProperty("level")
	private int level;

	@JsonProperty("locationId")
	private List<Integer> locationId = new ArrayList<>();

	@JsonProperty("fromTime")
	private String fromTime;

	@JsonProperty("type")
	private int type;

	@JsonProperty("intervalMin")
	private int intervalMin;

	@JsonProperty("startDate")
	private String startDate;

	@JsonProperty("toTime")
	private String toTime;


	public ConditionParamValue randomize(){
		this.level = 5;
		this.startDate = getCurrentDate();
		this.fromTime = "00:00";
		this.toTime = "23:59";
		this.intervalMin = 60;
		this.type = 14;
		this.locationId.add(4189);

		return this;
	}
}