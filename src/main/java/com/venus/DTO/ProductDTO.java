package com.venus.DTO;

import lombok.Data;

@Data
public class ProductDTO {
	private int id;
	private String name;
	private String image;
	private double price;
	private int available;
	private String categoryName;
	private int categoryId;
	private String createdAt;
	private int status;
	private int isSale;
	private String description;
}
