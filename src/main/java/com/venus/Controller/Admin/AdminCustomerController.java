package com.venus.Controller.Admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String index(@RequestParam(value = "keyword", defaultValue = "") String keyword,
			@RequestParam(value = "order_by", defaultValue = "DESSC") String orderBy,
			@RequestParam(value = "sort_by", defaultValue = "fullName") String sortBy) {
		Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE,
				Sort.by(orderBy.equals("DESC") ? Direction.DESC : Direction.ASC, sortBy));

		Page<User> listCustomer = userRepository.findByFullNameContainingOrEmailContaining(keyword, keyword, pageable);

		sessionService.set("listCustomer", listCustomer.getContent());
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

	@GetMapping("/{id}")
	public String detail(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			sessionService.set("customer", user.get());
		}
		return "admin/admin-customer-single";
	}

}
