package com.venus.Controller.Client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestimonialController {

	@GetMapping("/testimonial")
	public String Testimonial() {
		return "testimonial";
	}
}