package com.venus.Controller.Client;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.venus.Service.SessionService;
import com.venus.Service.UserService;
import com.venus.entities.User;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	SessionService sessionService;

	@Autowired
	UserService userService;

	@GetMapping({ "", "/information" })
	public String infor(Model model) {
		User user = sessionService.get("user");
		model.addAttribute("userAttribute", user);
		return "account-information";
	}

	@PostMapping({ "", "/information" })
	public String update(Model model, @Valid @ModelAttribute("userAttribute") User user, BindingResult result) {
		model.addAttribute("userAttribute", user);
		if (result.hasErrors()) {
			result.getAllErrors().forEach(item -> System.out.println(item.getDefaultMessage()));
			result.getAllErrors().forEach(item -> System.out.println(item.getDefaultMessage()));
			return "account-information";
		}
		User mainUser = sessionService.get("user");
		user.setCreatedAt(mainUser.getCreatedAt());
		user.setId(mainUser.getId());
		user.setRule(mainUser.getRule());
		user.setPassword(mainUser.getPassword());
		userService.update(user);

		return "redirect:/account";
	}

	@GetMapping("/orders")
	public String orders() {
		return "account-orders";
	}

	@GetMapping("/password")
	public String password() {
		return "account-password";
	}

	@PostMapping("/password")
	public String updatePassword(@RequestParam("old-password") String oldPassword,
			@RequestParam("new-password") String newPassword,
			@RequestParam("confirm-new-password") String confirmNewPassword) {
		User user = sessionService.get("user");

		// Kiểm tra xem mật khẩu cũ có khớp với mật khẩu hiện tại của người dùng hay
		// không
		if (!user.getPassword().equals(oldPassword)) {
			return "redirect:/account/password?resetPasswordError=your password is invalid";
		}

		// Kiểm tra xem mật khẩu mới và xác nhận mật khẩu mới có khớp nhau hay không
		if (!newPassword.equals(confirmNewPassword)) {
			return "redirect:/account?resetPasswordError=your-confirm-password-is-invalid";
		}

		// Cập nhật mật khẩu mới cho người dùng
		user.setPassword(newPassword);

		// Thực hiện các xử lý khác liên quan đến cập nhật mật khẩu, ví dụ như lưu thông

		userService.update(user);

		return "redirect:/account";
	}
}
