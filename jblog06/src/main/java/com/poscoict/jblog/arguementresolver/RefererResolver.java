package com.poscoict.jblog.arguementresolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.poscoict.jblog.annotation.Principal;
import com.poscoict.jblog.annotation.Referer;
import com.poscoict.jblog.vo.UserVo;

public class RefererResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Referer ref = parameter.getParameterAnnotation(Referer.class);
		if(ref ==null) {
			return false;
		}
		
		if(parameter.getParameterType().equals(String.class)==false) {
			return false;
		}

		return true;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return ((HttpServletRequest)webRequest.getNativeRequest()).getHeader("Referer");
	}

}
