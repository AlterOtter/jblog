package com.poscoict.jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.jblog.repository.UserRepository;
import com.poscoict.jblog.vo.UserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	UserRepository urepository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String user_id =request.getParameter("user_id");
		String user_pw =request.getParameter("password");
		UserVo vo = new UserVo();
		vo.setUser_id(user_id);
		vo.setPassword(user_pw);
		
		if(user_id.isBlank()||user_pw.isBlank()) {
			request.setAttribute("UserVo", vo);
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
			return false;
		}
		
		UserVo result_vo=urepository.login(vo);
		
		if(result_vo==null) {
			request.setAttribute("UserVo", vo);
			request.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(request, response);
			return false;
		}
		
		HttpSession sess = request.getSession();
		sess.setAttribute("authvo", result_vo);
		response.sendRedirect(request.getContextPath()+"/main");
		
		
		return false;
	}
}
