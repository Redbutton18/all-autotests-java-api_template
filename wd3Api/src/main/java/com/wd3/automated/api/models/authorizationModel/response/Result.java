package com.wd3.automated.api.models.authorizationModel.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.annotation.Generated;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Result{

	@JsonProperty("message")
	private String message;

	@JsonProperty("token")
	private String token;
}