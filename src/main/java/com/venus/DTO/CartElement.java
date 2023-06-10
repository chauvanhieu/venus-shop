package com.venus.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartElement {

	int id;
	String name;
	String image;
	double price;
	int quantity = 1;

}
