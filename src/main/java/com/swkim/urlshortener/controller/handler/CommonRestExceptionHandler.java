package com.swkim.urlshortener.controller.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.swkim.urlshortener.exception.ShortenerException;
import com.swkim.urlshortener.type.JsonResult;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(annotations = RestController.class)
public class CommonRestExceptionHandler {
	
	@ResponseBody
	@ExceptionHandler(ShortenerException.class)
	public JsonResult defaultExceptionHandler(ShortenerException e, HttpServletRequest request) {
		int errorCode = e.getErrorCode();
		String errorMessage = e.getErrorMessage();
		
		return JsonResult.fail(errorCode, errorMessage);
	}
}