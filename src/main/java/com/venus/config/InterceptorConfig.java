package com.venus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.venus.interceptor.AdminAuthInterceptor;
import com.venus.interceptor.CountElementShoppingCartInterceptor;
import com.venus.interceptor.LoginInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	LoginInterceptor loginInterceptor;
	@Autowired
	AdminAuthInterceptor adminAuthInterceptor;
	@Autowired
	CountElementShoppingCartInterceptor countShoppingCartInterceptor;


	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		LocaleChangeInterceptor locale = new LocaleChangeInterceptor();
		locale.setParamName("lang");
		registry.addInterceptor(locale).addPathPatterns("/**");

		// Logined
		registry.addInterceptor(loginInterceptor).addPathPatterns("/shopping-cart/**", "/account/**")
				.excludePathPatterns("/reset-password", "/forgot-password", "/", "/product", "/about", "/why-us",
						"/login", "/testimonial", "/admin/**");

		// Kiếm tra đăng nhập trang admin
		registry.addInterceptor(adminAuthInterceptor).addPathPatterns("/admin/**").excludePathPatterns(
				"/reset-password", "/forgot-password", "/product", "/", "/about", "/why-us", "/login", "/testimonial");

		registry.addInterceptor(countShoppingCartInterceptor).addPathPatterns("/**")
				.excludePathPatterns("/reset-password", "/forgot-password", "/admin/**");

	}
}
