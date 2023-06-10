package com.venus.Controller.Admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.venus.Service.SessionService;
import com.venus.entities.User;
import com.venus.repository.UserRepository;

@Controller
@RequestMapping("/admin/customer")
public class AdminCustomerController {

	@Autowired
	SessionService sessionService;
	@Autowired
	UserRepository userRepository;

	@GetMapping
	public String index() {
		List<User> listCustomer = userRepository.findByRule(0);
		sessionService.set("listCustomer", listCustomer);
		return "admin/admin-customer";
	}

	@PostMapping("/update")
	public String update(@RequestParam("id") int id, @RequestParam(value = "status", required = false) Object status) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			User u = user.get();
			u.setStatus(status == null ? 0 : 1);
			userRepository.save(u);
		}
		return "redirect:/admin/customer";
	}

}
