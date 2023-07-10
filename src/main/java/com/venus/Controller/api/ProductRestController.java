package com.venus.Controller.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.venus.DTO.Output;
import com.venus.DTO.ProductDTO;
import com.venus.converter.ProductConverter;
import com.venus.entities.Category;
import com.venus.entities.Product;
import com.venus.repository.CategoryRepository;
import com.venus.repository.ProductRepository;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

	private static final int DEFAULT_PAGE = 1;
	private static final int DEFAULT_LIMIT = 999999;
	private static final double MIN_PRICE = Double.MIN_VALUE;
	private static final double MAX_PRICE = Double.MAX_VALUE;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductConverter productConverter;

	@GetMapping
	public ResponseEntity<Output<List<ProductDTO>>> getAll(
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "999999") int limit,
			@RequestParam(value = "category_id", defaultValue = "0") int categoryID,
			@RequestParam(value = "order_by", defaultValue = "desc") String orderBy,
			@RequestParam(value = "sort_by", defaultValue = "price") String sortBy,
			@RequestParam(value = "range", defaultValue = "mọi khoảng giá") String rangeOption) {

		page = Math.max(DEFAULT_PAGE, page);
		limit = Math.max(DEFAULT_LIMIT, limit);
		double min = MIN_PRICE;
		double max = MAX_PRICE;

		switch (rangeOption) {
		case "dưới 5 triệu":
			min = 0;
			max = 4999999;
			break;
		case "5 đến 10 triệu":
			min = 5000000;
			max = 10000000;
			break;
		case "10 đến 20 triệu":
			min = 10000000;
			max = 20000000;
			break;
		case "20 đến 30 triệu":
			min = 20000000;
			max = 30000000;
			break;
		case "trên 30 triệu":
			min = 30000000;
			max = MAX_PRICE;
			break;
		case "mọi khoảng giá":
			min = MIN_PRICE;
			max = MAX_PRICE;
			break;
		}

		Optional<Category> category = categoryRepository.findById(categoryID);

		Pageable pageable = PageRequest.of(page - 1, limit, Sort
				.by(orderBy.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy.toLowerCase()));

		Page<Product> pageProduct;

		if (category.isEmpty()) {
			pageProduct = productRepository.findByNameContainingAndStatusAndPriceBetween(keyword, 1, min, max,
					pageable);
		} else {
			if (category.get().getStatus() == 1) {
				pageProduct = productRepository.findByCategoryAndNameContainingAndStatusAndPriceBetween(category.get(),
						keyword, 1, min, max, pageable);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		}

		if (pageProduct.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		List<ProductDTO> DTOs = new ArrayList<>();
		for (Product item : pageProduct) {
			DTOs.add(productConverter.toDTO(item));
		}

		Output<List<ProductDTO>> output = new Output<>();
		output.setData(DTOs);
		output.setLimit(limit);
		output.setPage(page);
		output.setTableName("Products");
		output.setTotalPages(pageProduct.getTotalPages());

		return new ResponseEntity<>(output, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getById(@PathVariable("id") int id) {
		Optional<Product> existingItemOptional = productRepository.findById(id);

		if (existingItemOptional.isPresent()) {
			ProductDTO DTO = productConverter.toDTO(existingItemOptional.get());
			return new ResponseEntity<>(DTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO item) {
		try {
			Product product = productRepository.save(productConverter.toEntity(item));
			return new ResponseEntity<>(productConverter.toDTO(product), HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable("id") int id, @RequestBody ProductDTO item) {
		boolean exists = productRepository.existsById(id);

		if (exists) {
			Product product = productRepository.save(productConverter.toEntity(item));
			return new ResponseEntity<>(productConverter.toDTO(product), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
		try {
			Optional<Product> existingItemOptional = productRepository.findById(id);
			if (existingItemOptional.isPresent()) {
				Product product = existingItemOptional.get();
				product.setStatus(0);
				productRepository.save(product);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}
}
