package com.venus.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.venus.Service.JWTService;

@Service
public class AuthProductInterceptor implements HandlerInterceptor {
	@Autowired
	JWTService jwtService;

	private final static String TOKEN_HEADER = "authorization";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		try {
			String path = request.getRequestURI();
			String method = request.getMethod();
			HttpServletRequest httpRequest = (HttpServletRequest) request;

			String authToken = httpRequest.getHeader(TOKEN_HEADER);

			if (!jwtService.validateTokenLogin(authToken)) {
				System.out.println("chưa đăng nhập");
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
				return false;
			}

			if (path.contains("/api/products") && method.equals("GET")) {
				return true;
			} else if (path.contains("/api/products")
					&& (method.equals("POST") || method.equals("PUT") || method.equals("DELETE"))) {
				if (!jwtService.acceptRule(authToken, 1)) {
					response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền thực hiện Request");
				}
				return jwtService.acceptRule(authToken, 1);
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}
}
