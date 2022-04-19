package com.spring.server.exception;

public class NotFoundResourceException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotFoundResourceException(String message) {
		super(message);
	}
	
	public static class NotFoundRoleException extends NotFoundResourceException {

		private static final long serialVersionUID = 1L;

		public NotFoundRoleException() {
			super("Not Found Role Error!!!");
		}
	}
}
