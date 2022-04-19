package com.spring.server.exception;

public class DuplicateResourceException extends Exception  {

	private static final long serialVersionUID = 1L;

	public DuplicateResourceException(String message) {
		super(message);
	}
	
	public static class DuplicateRoleNameException extends DuplicateResourceException {

		private static final long serialVersionUID = 1L;

		public DuplicateRoleNameException() {
			super("Role Name Duplicate Error!!!");
		}
	}
}
