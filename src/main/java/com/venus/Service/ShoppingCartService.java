package com.venus.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.venus.DTO.CartElement;

@Service
public interface ShoppingCartService {
	void add(int id);

	void remove(int id);

	void update(int id, int quantity);

	void clear();

	int getCount();

	double getAmount();

	List<CartElement> getList();

}
