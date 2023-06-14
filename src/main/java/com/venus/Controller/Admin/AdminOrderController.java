package com.venus.Controller.Admin;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.venus.Service.SessionService;
import com.venus.entities.Order;
import com.venus.repository.OrderRepository;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	SessionService sessionService;

	@GetMapping
	public String index(@RequestParam(value = "keyword", required = false) Optional<Integer> id,
			@RequestParam(value = "order_by", defaultValue = "DESC") String orderBy,
			@RequestParam(value = "sort_by", defaultValue = "createdAt") String sortBy) {

		Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE,
				Sort.by(orderBy.equals("DESC") ? Direction.DESC : Direction.ASC, sortBy));
		Page<Order> listOrder = null;
		if (id.isEmpty()) {
			listOrder = orderRepository.findAll(pageable);
		} else {
			listOrder = orderRepository.findByIdLike(id.get(), pageable);
		}

		sessionService.set("listOrder", listOrder.getContent());
		return "admin/admin-order";
	}

	@GetMapping("/{id}")
	public String detail(@PathVariable int id) {
		Optional<Order> order = orderRepository.findById(id);
		if (order.isEmpty()) {
			return "redirect:/admin/order";
		}

		sessionService.set("order", order.get());
		return "admin/admin-order-single";
	}

	@PostMapping("/confirm")
	public String confirmOrder(@RequestParam int id, @RequestParam(value = "status", required = false) Object status) {
		try {
			Optional<Order> order = orderRepository.findById(id);
			if (order.isPresent()) {
				Order o = order.get();
				o.setStatus(status != null ? 1 : 0);
				orderRepository.save(o);
			}
		} catch (Exception e) {
			System.out.println("Lỗi xác nhận thanh toán :" + e.getMessage());
		}
		return "redirect:/admin/order/" + id;
	}

}
