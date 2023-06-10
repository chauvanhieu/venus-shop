package com.venus.Controller.Admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.venus.Service.SessionService;
import com.venus.entities.Category;
import com.venus.entities.Product;
import com.venus.repository.CategoryRepository;
import com.venus.repository.ProductRepository;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

	@Autowired
	SessionService sessionService;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping
	public String index(@RequestParam(value = "keyword", defaultValue = "") String keyword,
			@RequestParam(value = "category_id", defaultValue = "0") int categoryID,
			@RequestParam(value = "order_by", defaultValue = "DESSC") String orderBy,
			@RequestParam(value = "sort_by", defaultValue = "price") String sortBy,
			@RequestParam(value = "range", defaultValue = "mọi khoảng giá") String rangeOption) {
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

		Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE,
				Sort.by(orderBy.equals("DESC") ? Direction.DESC : Direction.ASC, sortBy));

		Page<Product> pageProduct = null;

		if (category.isEmpty()) {

			// Lấy tất cả các product theo tất cả category

			pageProduct = productRepository.findByNameContainingAndPriceBetween(keyword, min, max, pageable);

		} else {

			// Lấy product theo riêng category

			pageProduct = productRepository.findByCategoryAndNameContainingAndPriceBetween(category.get(), keyword, min,
					max, pageable);

		}

		List<Category> listCategory = categoryRepository.findAll();
		sessionService.set("listCategory", listCategory);
		sessionService.set("listProduct", pageProduct.getContent());
		return "admin/admin-product";
	}

	@PostMapping(value = "/update-status")
	public String updatestatus(@RequestParam(value = "status", required = false) Object status,
			@RequestParam("id") int id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			Product p = product.get();
			p.setStatus(status != null ? 1 : 0);
			productRepository.save(p);
		}
		return "redirect:/admin/product";
	}

	@PostMapping(value = "/update-issale")
	public String updateisSale(@RequestParam(value = "isSale", required = false) Object isSale,
			@RequestParam("id") int id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			Product p = product.get();
			p.setIsSale(isSale != null ? 1 : 0);
			productRepository.save(p);
		}
		return "redirect:/admin/product";
	}
}
