package com.spring.server.domain.role;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.spring.server.exception.ConverteEnumException.ConverteAccessMethodException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccessMethod {
	ALL("*"), GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE");

	private final String code;

	@JsonCreator
	public static AccessMethod fromJson(String string) {
		try {
			return AccessMethod.valueOf(string);
		} catch (Exception e) {
			throw new ConverteAccessMethodException();
		}
	}
	
	public static class AccessMethodAttributeConverter implements AttributeConverter<AccessMethod, String> {

		@Override
		public String convertToDatabaseColumn(AccessMethod attribute) {
			if (attribute != null) {
				return attribute.getCode();
			} else {
				throw new ConverteAccessMethodException();
			}
		}

		@Override
		public AccessMethod convertToEntityAttribute(String dbData) {
			AccessMethod[] apiMethods = AccessMethod.values();
			for (AccessMethod apiMethod : apiMethods) {
				if (apiMethod.getCode().equals(dbData)) return apiMethod;
			}
			throw new ConverteAccessMethodException();
		}
	}
	
}
