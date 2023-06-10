package com.venus.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.venus.Service.SessionService;
import com.venus.entities.User;

@Service
public class AdminAuthInterceptor implements HandlerInterceptor {
	@Autowired
	SessionService sessionService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User admin = sessionService.get("admin");
		if (admin == null) {
			response.sendRedirect("/login");
			return false;
		}

		if (admin != null && admin.getRule() != 1) {
			sessionService.remove("admin");
			response.sendRedirect("/login");
			return false;
		}
		return true;
	}
}
