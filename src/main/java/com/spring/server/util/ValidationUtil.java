package com.spring.server.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.spring.server.exception.BadParameterException;

public final class ValidationUtil {

	public static void checkFieldErrors(BindingResult bindingResult) throws BadParameterException {
		if (bindingResult.hasFieldErrors()) {
			List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
			Map<String, String> fieldErrorMap = new HashMap<>();

			for (FieldError fieldError : fieldErrorList) {
				String key = fieldError.getCode() + "-" + fieldError.getField();
				fieldErrorMap.put(key, fieldError.getDefaultMessage());
			}

			throw new BadParameterException(fieldErrorMap);
		}

	}
	
	public static boolean isBlank(String value) {
		return (value == null || value.trim().length() == 0);
	}

	public static boolean isNotBlank(String value) {
		return !isBlank(value);
	}

	public static boolean isOver(String value, int max) {
		return isNotBlank(value) && (value.length() > max);
	}

	public static boolean isOver(int value, int max) {
		return (value > max);
	}

	public static boolean isUnder(String value, int min) {
		return isNotBlank(value) && (value.length() < min);
	}

	public static boolean isUnder(int value, int min) {
		return (value < min);
	}

	public static boolean outOfRange(String value, int min, int max) {
		return (isOver(value, max) || isUnder(value, min));
	}

	public static boolean outOfRange(int value, int min, int max) {
		return (isOver(value, max) || isUnder(value, min));
	}

	public static boolean notMatch(String regexp, String value) {
		return !Pattern.matches(regexp, value);
	}
}
