package com.venus.Controller.Client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.venus.Service.SessionService;
import com.venus.entities.Product;
import com.venus.repository.ProductRepository;

@Controller
public class HomeController {

	@Autowired
	SessionService sessionService;
	@Autowired
	ProductRepository productRepository;

	@GetMapping("/")
	public String hello() {
		List<Product> Top12Product = productRepository.findTop12ByStatusOrderByPriceDesc();
		List<Product> ProductSaling = productRepository.findByStatusAndIsSale();
		sessionService.set("listProduct", Top12Product);
		sessionService.set("productSaling", ProductSaling);
		return "index";
	}

}
