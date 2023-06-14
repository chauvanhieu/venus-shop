package com.venus.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venus.entities.Order;
import com.venus.entities.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	Page<Order> findByIdLike(int id, Pageable pageable);

	List<Order> findByUser(User user, Sort sort);
}
