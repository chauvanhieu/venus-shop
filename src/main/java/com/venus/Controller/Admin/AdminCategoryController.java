package com.venus.Controller.Admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.venus.Service.SessionService;
import com.venus.entities.Category;
import com.venus.repository.CategoryRepository;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	SessionService sessionService;

	@GetMapping
	public String index() {
		List<Category> listCategories = categoryRepository.findAll();
		sessionService.set("listCategories", listCategories);
		return "admin/admin-categories";
	}

	@PostMapping("/add")
	public String add(@RequestParam String name) {
		if (name.equals("")) {
			return "redirect:/admin/category?error=true";
		}
		Category category = new Category();
		category.setName(name);
		category.setStatus(1);
		try {
			categoryRepository.save(category);
		} catch (Exception e) {
			System.out.println("Lỗi thêm danh mục");
		}
		return "redirect:/admin/category";
	}

	@PostMapping("/update")
	public String update(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam(value = "status", required = false) Object status) {
		if (name.equals("")) {

			return "redirect:/admin/category?error=true";
		}
		Category category = new Category();
		category.setId(id);
		category.setName(name);
		category.setStatus(status != null ? 1 : 0);
		try {
			categoryRepository.save(category);
		} catch (Exception e) {
			System.out.println("Lỗi update danh mục");
		}
		return "redirect:/admin/category";
	}
}
