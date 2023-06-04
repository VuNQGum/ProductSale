package com.hust.productsale.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

	private final Object data;
	private final Boolean state;
	private final String timestamp;
	private final String cause;
	private final String path;
	private final String message;

	public ApiResponse(Boolean state, Object data, String cause, String path, String message) {
		this.timestamp = Instant.now().toString();
		this.data = data;
		this.state = state;
		this.cause = cause;
		this.path = path;
		this.message = message;
	}

	public ApiResponse(Boolean state, Object data) {
		this.timestamp = Instant.now().toString();
		this.data = data;
		this.state = state;
		this.cause = null;
		this.path = null;
		this.message = null;
	}

	public ApiResponse(Boolean state, Object data, String message) {
		this.timestamp = Instant.now().toString();
		this.data = data;
		this.state = state;
		this.cause = null;
		this.path = null;
		this.message = message;
	}
}
