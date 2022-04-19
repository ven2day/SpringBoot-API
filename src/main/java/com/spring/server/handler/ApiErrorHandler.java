package com.spring.server.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.server.domain.ApiErrorResponse;
import com.spring.server.exception.BadParameterException;
import com.spring.server.exception.DuplicateResourceException;
import com.spring.server.exception.NotFoundResourceException;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiErrorHandler extends ResponseEntityExceptionHandler{

	private final ObjectMapper objectMapper;

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handle500(Exception ex, WebRequest request) {
		HttpHeaders headers = new HttpHeaders();
		return handleExceptionInternal(ex, null, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println(ex.getClass());
		return super.handleExceptionInternal(ex,
				ApiErrorResponse.builder()
					.bussinessError(false)
					.message(ex.getMessage()).build(),
				headers, status, request);
	}
	
	@ExceptionHandler(BadParameterException.class)
	protected ResponseEntity<ApiErrorResponse> handle400(BadParameterException ex) throws JsonProcessingException {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				ApiErrorResponse.builder()
					.bussinessError(true)
					.message(objectMapper.writeValueAsString(ex.getFieldErrorMap())).build());
	}

	@ExceptionHandler(NotFoundResourceException.class)
	protected ResponseEntity<ApiErrorResponse> handle404(NotFoundResourceException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				ApiErrorResponse.builder()
				.bussinessError(true)
				.message(ex.getMessage()).build());
	}

	@ExceptionHandler(DuplicateResourceException.class)
	protected ResponseEntity<ApiErrorResponse> handle409(DuplicateResourceException ex) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(
				ApiErrorResponse.builder()
					.bussinessError(true)
					.message(ex.getMessage()).build());
	}

}
