package com.venus.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.venus.Service.SessionService;
import com.venus.Service.ShoppingCartService;
import com.venus.entities.User;

@Service
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	SessionService sessionService;
	@Autowired
	ShoppingCartService shoppingCartService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		User user = sessionService.get("user");
		if (user == null) {
			response.sendRedirect("/store-login");
			return false;
		}
		if (user.getStatus() == 0) {
			response.sendRedirect("/store-login");
			return false;
		}
		sessionService.set("countCartElement", shoppingCartService.getCount());
		return true;
	}
}
