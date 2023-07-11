package com.venus.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.venus.DTO.ProductDTO;
import com.venus.entities.Category;
import com.venus.entities.Product;
import com.venus.repository.CategoryRepository;
import com.venus.repository.ProductRepository;

@Component
public class ProductConverter {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	public Product toEntity(ProductDTO DTO) {
		Optional<Product> product = productRepository.findById(DTO.getId());
		Product item = new Product();
		if (product.isPresent()) {
			item = product.get();
		}

		Optional<Category> category = categoryRepository.findById(DTO.getCategoryId());

		if (category.isPresent()) {
			item.setCategory(category.get());
		}

		item.setName(DTO.getName());
		item.setAvailable(DTO.getAvailable());
		item.setCreatedAt(DTO.getCreatedAt());
		item.setDescription(DTO.getDescription());
		item.setImage(DTO.getImage());
		item.setIsSale(DTO.getIsSale());
		item.setPrice(DTO.getPrice());
		item.setStatus(DTO.getStatus());
		return item;
	}

	public ProductDTO toDTO(Product entity) {
		ProductDTO dto = new ProductDTO();

		dto.setId(entity.getId());

		dto.setAvailable(entity.getAvailable());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setDescription(entity.getDescription());
		dto.setImage(entity.getImage());
		dto.setIsSale(entity.getIsSale());
		dto.setName(entity.getName());
		dto.setPrice(entity.getPrice());
		dto.setStatus(entity.getStatus());

		if (entity.getCategory() != null) {
			dto.setCategoryName(entity.getCategory().getName());
			dto.setCategoryId(entity.getCategory().getId());
		}
		return dto;
	}
}
