package com.spring.server.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.server.SpringBootApiConstants;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiErrorResponse  {
	
	private	String message;
	
	private boolean bussinessError;
	
	@JsonFormat(pattern = SpringBootApiConstants.DATE_TIME_FORMAT)
	private LocalDateTime timestamp;

	@Builder
	public ApiErrorResponse(boolean bussinessError, String message) {
		super();
		this.message = message;
		this.bussinessError = bussinessError;
		this.timestamp = LocalDateTime.now();
	}
}