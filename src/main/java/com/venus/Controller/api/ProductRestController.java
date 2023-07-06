package com.venus.Controller.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping
	public ResponseEntity<Output<List<ProductDTO>>> getAll(
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "category_id", defaultValue = "0") int categoryID,
			@RequestParam(value = "order_by", defaultValue = "desc") String orderBy,
			@RequestParam(value = "sort_by", defaultValue = "price") String sortBy,
			@RequestParam(value = "range", defaultValue = "mọi khoảng giá") String rangeOption) {
		try {
			if (page < 1) {
				page = 1;
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

			Pageable pageable = PageRequest.of(page - 1, limit, Sort
					.by(orderBy.toLowerCase().equals("desc") ? Direction.DESC : Direction.ASC, sortBy.toLowerCase()));

			Page<Product> pageProduct = null;

			if (category.isEmpty()) {

				// Lấy tất cả các product theo tất cả category

				pageProduct = productRepository.findByNameContainingAndStatusAndPriceBetween(keyword, 1, min, max,
						pageable);

			} else {

				// Lấy product theo riêng category
				if (category.get().getStatus() == 1) {
					pageProduct = productRepository.findByCategoryAndNameContainingAndStatusAndPriceBetween(
							category.get(), keyword, 1, min, max, pageable);
				} else {
					pageProduct = null;
				}

			}

			if (pageProduct.getContent().size() == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			Output<List<ProductDTO>> items = new Output<List<ProductDTO>>();
			List<ProductDTO> DTOs = new ArrayList<>();
			for (Product item : pageProduct.getContent()) {
				DTOs.add(ProductConverter.toDTO(item));
			}
			items.setData(DTOs);
			items.setLimit(limit);
			items.setPage(page);
			items.setTableName("Products");
			items.setTotalPages(pageProduct.getTotalPages());
			return new ResponseEntity<>(items, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

//	@GetMapping("{id}")
//	public ResponseEntity<entityClassName> getById(@PathVariable("id") entityIdType id) {
//		Optional<entityClassName> existingItemOptional = repository.findById(id);
//
//		if (existingItemOptional.isPresent()) {
//			return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@PostMapping
//	public ResponseEntity<entityClassName> create(@RequestBody entityClassName item) {
//		try {
//			entityClassName savedItem = repository.save(item);
//			return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
//		} catch (Exception e) {
//			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
//		}
//	}
//
//	@PutMapping("{id}")
//	public ResponseEntity<entityClassName> update(@PathVariable("id") entityIdType id,
//			@RequestBody entityClassName item) {
//		Optional<entityClassName> existingItemOptional = repository.findById(id);
//		if (existingItemOptional.isPresent()) {
//			entityClassName existingItem = existingItemOptional.get();
//			System.out
//					.println("TODO for developer - update logic is unique to entity and must be implemented manually.");
//			// existingItem.setSomeField(item.getSomeField());
//			return new ResponseEntity<>(repository.save(existingItem), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
//
//	@DeleteMapping("{id}")
//	public ResponseEntity<HttpStatus> delete(@PathVariable("id") entityIdType id) {
//		try {
//			repository.deleteById(id);
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//		}
//	}

}
