package com.venus.Controller.Client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/doc")
public class DocController {

	@GetMapping
	public String index() {
		return "redirect:/swagger-ui/index.html";
	}
}
