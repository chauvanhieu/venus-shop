package com.venus.Controller.Admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.venus.DTO.BestSalerReport;
import com.venus.Service.SessionService;
import com.venus.entities.User;
import com.venus.repository.BestSalerReportRepository;
import com.venus.repository.UserRepository;

@Controller

public class AdminController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	SessionService sessionService;
	@Autowired
	BestSalerReportRepository reportRepository;

	@GetMapping("/admin")
	public String index() {
		User admin = sessionService.get("admin");
		if (admin == null || admin.getRule() != 1) {
			sessionService.remove("admin");
			return "redirect:/login";
		}
		List<BestSalerReport> report = reportRepository.getBestSellers();
		sessionService.set("reportData", report);
		return "admin/admin-index";
	}

	@GetMapping("/login")
	public String login() {
		if (sessionService.get("admin") != null) {
			return "redirect:/admin";
		}
		return "admin/admin-login";
	}

	@PostMapping("/login")
	public String handleLogin(@RequestParam("email") String email, @RequestParam("password") String password) {

		Optional<User> user = userRepository.findByEmailAndPassword(email, password);
		if (user.isEmpty()) {
			return "redirect:/admin/login?errorMessage=Email or password is incorrect";
		} else {
			sessionService.set("admin", user.get());

			return "redirect:/admin";
		}
	}

	@GetMapping("/admin/logout")
	public String logout() {
		sessionService.remove("admin");
		return "redirect:/login";
	}
}
