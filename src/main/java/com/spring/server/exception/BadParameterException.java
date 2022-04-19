package com.spring.server.exception;

import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BadParameterException extends Exception {

	private static final long serialVersionUID = 1L;

	private final Map<String, String> fieldErrorMap;
}
