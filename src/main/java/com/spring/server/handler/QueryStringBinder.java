package com.spring.server.handler;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class QueryStringBinder {

	@InitBinder
	public void initBinderForEvent(WebDataBinder webDataBinder) { 
		webDataBinder.initDirectFieldAccess();
	}

}
