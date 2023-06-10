package com.venus.Controller.Client;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.venus.DTO.CartElement;
import com.venus.Service.SessionService;
import com.venus.Service.ShoppingCartService;
import com.venus.entities.Product;
import com.venus.repository.ProductRepository;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

	@Autowired
	ShoppingCartService shoppingCartService;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	SessionService sessionService;

	@GetMapping
	public String index() {
		return "shopping-cart";
	}

	@GetMapping("/add/{id}")
	public String add(@PathVariable int id) {

		shoppingCartService.add(id);

		List<CartElement> list = shoppingCartService.getList();
		sessionService.set("ShoppingCart", list);
		return "redirect:/shopping-cart";
	}

	@GetMapping("/remove/{id}")
	public String remove(@PathVariable int id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			shoppingCartService.remove(id);
		}
		return "redirect:/shopping-cart";
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, @RequestParam int quantity) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			shoppingCartService.update(id, quantity);
		}
		return "redirect:/shopping-cart";
	}
}
