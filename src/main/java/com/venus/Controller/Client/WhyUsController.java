package com.venus.Controller.Client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WhyUsController {

	@GetMapping("/why-us")
	public String whyUs() {
		return "why-us";
	}
}
