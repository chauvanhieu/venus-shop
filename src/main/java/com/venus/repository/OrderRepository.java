package com.venus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venus.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
