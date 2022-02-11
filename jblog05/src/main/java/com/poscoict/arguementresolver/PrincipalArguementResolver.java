package com.poscoict.arguementresolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.poscoict.annotation.Principal;
import com.poscoict.jblog.vo.UserVo;

public class PrincipalArguementResolver implements HandlerMethodArgumentResolver{
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		if(!supportsParameter(parameter)) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		HttpSession session = request.getSession();
		if(session == null) {
			return null;
		}
		
		return session.getAttribute("authvo");
	}
	
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Principal ppl = parameter.getParameterAnnotation(Principal.class);
		if(ppl ==null) {
			return false;
		}
		
		if(parameter.getParameterType().equals(UserVo.class)==false) {
			return false;
		}
		
		return true;
	}


}
