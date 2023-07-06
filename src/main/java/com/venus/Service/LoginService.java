package com.venus.Service;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venus.entities.User;
import com.venus.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	SessionService sessionService;
	@Autowired
	ParamService paramService;
	@Autowired
	HttpServletResponse response;

	public void login() {
		String email = paramService.getString("email");
		String password = paramService.getString("password");
		Optional<User> user = userRepository.findByEmail(email);
		if (user.isEmpty()) {
			sessionService.set("LoginMessage", "Tài khoản không tồn tại !");
		} else {
			if (user.get().getPassword().equals(password)) {
				if (user.get().getStatus() == 0) {
					sessionService.set("LoginMessage", "Tài khoản bị khóa !");
					return;
				}
				sessionService.set("user", user.get());
				sessionService.set("LoginMessage", "");
				try {
					response.sendRedirect("/");
				} catch (Exception e) {
					System.out.println("Lỗi đăng nhập");
				}
			} else {
				sessionService.set("LoginMessage", "Mật khẩu không chính xác !");
			}
		}
	}
}
