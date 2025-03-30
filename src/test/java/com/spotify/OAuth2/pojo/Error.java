package com.spotify.OAuth2.pojo;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Error {

	@JsonProperty("error")
	private InnerError error;

	@JsonProperty("error")
	public InnerError getError() {
		return error;
	}

	@JsonProperty("error")
	public void setError(InnerError error) {
		this.error = error;
	}

}