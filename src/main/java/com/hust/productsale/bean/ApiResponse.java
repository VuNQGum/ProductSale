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

	public ApiResponse(Boolean state, Object data, String cause, String path) {
		this.timestamp = Instant.now().toString();
		this.data = data;
		this.state = state;
		this.cause = cause;
		this.path = path;
	}

	public ApiResponse(Boolean state, Object data) {
		this.timestamp = Instant.now().toString();
		this.data = data;
		this.state = state;
		this.cause = null;
		this.path = null;
	}

}
