package com.venus.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.venus.entities.Category;
import com.venus.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("select p from Product p where p.status=1 and p.category.status=1")
	List<Product> findTop12ByStatusOrderByPriceDesc();

	List<Product> findByStatus(int status);

	@Query("select p from Product p where p.status=1 and p.category.status=1 and isSale=1")
	List<Product> findByStatusAndIsSale();

	Page<Product> findByCategoryAndNameContainingAndStatusAndPriceBetween(Category category, String name, int status,
			double min, double max, Pageable pageable);

	Page<Product> findByNameContainingAndStatusAndPriceBetween(String name, int status, double min, double max,
			Pageable pageable);

	Page<Product> findByCategoryAndNameContainingAndPriceBetween(Category category, String name, double min, double max,
			Pageable pageable);

	Page<Product> findByNameContainingAndPriceBetween(String name, double min, double max, Pageable pageable);

	@Query("SELECT p from Product p WHERE p.category.status=1 and p.status=1")
	List<Product> getDataToBuy();

}
