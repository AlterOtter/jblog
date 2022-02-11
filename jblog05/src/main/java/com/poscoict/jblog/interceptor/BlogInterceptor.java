package com.poscoict.jblog.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.annotation.Auth;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.UserService;
import com.poscoict.jblog.vo.UserVo;

public class BlogInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	BlogService bservice;
	
	@Autowired
	UserService uservice;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		//블로그 전체 타이틀 
		Map map = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); 
		if(map.containsKey("id")) { // 경로에 PathVariable인 id 가 있는 경우
			Object obj=map.get("id");
			String user_id = extract_userId((String) obj); // 아이디 추출
				
			request.setAttribute("blogvo", bservice.getblogMaininfo(user_id));				//블로그 기본 양식
			request.setAttribute("blogMainPath", request.getContextPath()+"/blog/"+user_id); //기본 Path 지정
			//============================start	auth  권한 확인
			Auth auth = handlerMethod.getMethodAnnotation(Auth.class); 
			if(auth !=null) { //본인인지 권한 확인
				HttpSession sess=request.getSession();
				UserVo vo=(UserVo)sess.getAttribute("authvo");
				if(checkAuth(vo, user_id)==false) {
					response.sendRedirect(request.getContextPath()+"/blog/"+user_id);
					return false;
				}
			}
			//===============================End auth
			return true;
		}
		
		
		response.sendRedirect(request.getContextPath()+"/main");
		return false;
	}
	
	public String extract_userId(Object obj) {
		String user_id =null;
		if(obj instanceof String) {
			user_id = (String) obj;
		}
		
		return user_id;
	}
	
	public boolean checkAuth(UserVo vo,String blog_admin) {
		if(vo!=null) {
			if(vo.getUser_id().equals(blog_admin)) {
				return true;
			}
			
		}
		
		return false;
	}
}
