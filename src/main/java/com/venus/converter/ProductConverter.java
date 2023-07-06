package com.venus.converter;

import com.venus.DTO.ProductDTO;
import com.venus.entities.Product;

public class ProductConverter {

	public static Product toEntity(ProductDTO DTO) {

		return null;
	}

	public static ProductDTO toDTO(Product entity) {
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
