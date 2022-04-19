package com.spring.server.exception;

public class ConverteEnumException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConverteEnumException(String message) {
		super(message);
	}
	
	public static class ConverteAccessMethodException extends ConverteEnumException {

		private static final long serialVersionUID = 1L;

		public ConverteAccessMethodException() {
			super("AccessMethod Enum Converting Error!!!");
		}
	}
	
}
