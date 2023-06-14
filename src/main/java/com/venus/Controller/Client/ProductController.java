package com.venus.Controller.Client;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.venus.Service.SessionService;
import com.venus.entities.Category;
import com.venus.entities.Product;
import com.venus.repository.CategoryRepository;
import com.venus.repository.ProductRepository;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	SessionService sessionService;
	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping
	public String index(@RequestParam(value = "keyword", defaultValue = "") String keyword,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "category_id", defaultValue = "0") int categoryID,
			@RequestParam(value = "order_by", defaultValue = "DESSC") String orderBy,
			@RequestParam(value = "sort_by", defaultValue = "price") String sortBy,
			@RequestParam(value = "range", defaultValue = "mọi khoảng giá") String rangeOption) {
		if (page < 0) {
			page = 0;
		}
		if (limit < 1) {
			limit = 12;
		}
		double min = Double.MIN_VALUE;
		double max = Double.MAX_VALUE;

		// Lọc KHOẢNG GIÁ
		switch (rangeOption) {
		case "dưới 5 triệu":
			// Dưới 5 triệu
			min = 0;
			max = 4999999;
			break;
		case "5 đến 10 triệu":
			// 5 - 10 triệu
			min = 5000000;
			max = 10000000;
			break;
		case "10 đến 20 triệu":
			// 10 - 20 triệu
			min = 10000000;
			max = 20000000;
			break;
		case "20 đến 30 triệu":
			// 20 - 30 triệu
			min = 20000000;
			max = 30000000;
			break;
		case "trên 30 triệu":
			// Trên 30 triệu
			min = 30000000;
			max = Double.MAX_VALUE;
			break;
		case "mọi khoảng giá":
			// Trên 30 triệu
			min = Double.MIN_VALUE;
			max = Double.MAX_VALUE;
			break;
		default:
			break;
		}

		// Lấy Category
		Optional<Category> category = categoryRepository.findById(categoryID);

		Pageable pageable = PageRequest.of(page, limit,
				Sort.by(orderBy.equals("DESC") ? Direction.DESC : Direction.ASC, sortBy));

		Page<Product> pageProduct = null;

		if (category.isEmpty()) {

			// Lấy tất cả các product theo tất cả category

			pageProduct = productRepository.findByNameContainingAndStatusAndPriceBetween(keyword, 1, min, max,
					pageable);

		} else {

			// Lấy product theo riêng category
			if (category.get().getStatus() == 1) {
				pageProduct = productRepository.findByCategoryAndNameContainingAndStatusAndPriceBetween(category.get(),
						keyword, 1, min, max, pageable);
			} else {
				pageProduct = null;
			}

		}

		List<Category> listCategory = categoryRepository.findAll();
		sessionService.set("listCategory", listCategory);
		sessionService.set("pageProduct", pageProduct);
		return "products";
	}

	@GetMapping("/{id}")
	public String getProduct(@PathVariable("id") Optional<Integer> id) {
		if (id.isEmpty()) {
			try {
				response.sendRedirect("/");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Optional<Product> product = productRepository.findById(id.get());
		if (product.isEmpty()) {
			try {
				response.sendRedirect("/");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		sessionService.set("product", product.get());
		return "product-single";
	}
}
