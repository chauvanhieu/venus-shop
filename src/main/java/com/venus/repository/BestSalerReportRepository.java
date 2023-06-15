package com.venus.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.venus.DTO.BestSalerReport;

public interface BestSalerReportRepository extends JpaRepository<BestSalerReport, Serializable> {
	@Query("SELECT new com.venus.DTO.BestSalerReport(p.id, p.name, p.image, p.price, SUM(od.count), (SUM(od.count) * p.price)) "
			+ "FROM com.venus.entities.Product p " + "JOIN p.orderDetails od " + "JOIN od.order o "
			+ "WHERE o.status = 1 " + "GROUP BY p.id, p.name, p.image, p.price " + "ORDER BY SUM(od.count) DESC")
	List<BestSalerReport> getBestSellers();
}
