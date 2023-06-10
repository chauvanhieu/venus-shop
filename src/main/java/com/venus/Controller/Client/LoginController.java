package com.venus.Controller.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.venus.Service.LoginService;
import com.venus.Service.SessionService;

@Controller
public class LoginController {

	@Autowired
	SessionService sessionService;
	@Autowired
	LoginService loginService;

	@GetMapping("/store-login")
	public String login() {
		return "login-register";
	}

	@PostMapping("/store-login")
	public String handleLogin(@RequestParam String email, @RequestParam String password) {
		if (!email.equals("") && !password.equals("")) {
			loginService.login();
		}

		return "login-register";
	}

	@GetMapping("/logout")
	public String logout() {
		if (sessionService.get("user") != null) {
			sessionService.remove("user");
		}
		return "redirect:/";
	}

}
