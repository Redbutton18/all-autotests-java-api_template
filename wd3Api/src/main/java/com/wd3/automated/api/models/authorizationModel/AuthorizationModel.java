package com.wd3.automated.api.models.authorizationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class AuthorizationModel{

	@JsonProperty("login")
	private String login;

	@JsonProperty("password")
	private String password;
}