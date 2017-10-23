package com.swkim.urlshortener.controller.interceptor;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.swkim.urlshortener.annotation.CheckUrl;
import com.swkim.urlshortener.exception.ShortenerException;
import com.swkim.urlshortener.util.UrlUtil;

public class UrlInterceptor implements HandlerInterceptor {

	public static String regex = "^(https?):\\/\\/([^:\\/\\s]+)(:([^\\/]*))?((\\/[^\\s/\\/]+)*)?\\/?([^#\\s\\?]*)(\\?([^#\\s]*))?(#(\\w*))?$";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod hm = (HandlerMethod)handler; 
		Method method= hm.getMethod();
		
		if( method.getDeclaringClass().isAnnotationPresent(CheckUrl.class)
				|| hm.getMethodAnnotation(CheckUrl.class) != null){
			String url = request.getParameter("url");
			if (url == null || url == "") {
				throw new ShortenerException(-1).setErrorMessage("url is empty");
			}
			
			StringBuffer sb = new StringBuffer();
			sb.append(UrlUtil.decodeUrl(url));

			Pattern p = Pattern.compile(regex);
			if (!p.matcher(sb.toString()).matches()) {
				throw new ShortenerException(-2).setErrorMessage("url is invalid");
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	
}
