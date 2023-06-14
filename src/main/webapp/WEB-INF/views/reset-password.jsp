<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email is sending - Venus Shop</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
</head>
<body style="background-color: #97b1cb !important">
	<div class="container">
	
		<div class="form-container" style="width: 400px;margin:200px auto">
		<center>
			<h1>Venus Shop</h1>
			<h5>Thay đổi mật khẩu mới</h5>
		</center>
			<form id="reset-password-form" action="/reset-password" method="post">
				<input name="email" type="hidden" value="${email }">
				<div class="form-group">
					<label for="new-password">Nhập mật khẩu mới</label> <input
						name="new-password" id="new-password" type="password"
						class="form-control"> <span id="new-password-error"
						class="error-message"></span>
				</div>
				<div class="form-group">
					<label for="confirm-new-password">Nhập lại mật khẩu mới</label> <input
						name="confirm-new-password" id="confirm-new-password"
						type="password" class="form-control"> <span
						id="confirm-password-error" class="error-message"></span>
				</div>
				<center>
					<button class="btn btn-success" type="submit">Xác nhận</button>
				</center>
			</form>
		</div>
	</div>
</body>
<script>
	document
			.getElementById("reset-password-form")
			.addEventListener(
					"submit",
					function(event) {
						event.preventDefault(); // Ngăn chặn form submit mặc định

						// Lấy giá trị của các trường dữ liệu
						var newPassword = document
								.getElementById("new-password").value;
						var confirmNewPassword = document
								.getElementById("confirm-new-password").value;

						// Xóa thông báo lỗi cũ
						document.getElementById("new-password-error").textContent = "";
						document.getElementById("confirm-password-error").textContent = "";

						// Validate dữ liệu
						if (newPassword === "") {
							document.getElementById("new-password-error").textContent = "Vui lòng nhập mật khẩu mới";
							return; // Dừng lại nếu có lỗi
						}

						if (confirmNewPassword === "") {
							document.getElementById("confirm-password-error").textContent = "Vui lòng nhập lại mật khẩu mới";
							return; // Dừng lại nếu có lỗi
						}

						if (newPassword !== confirmNewPassword) {
							document.getElementById("confirm-password-error").textContent = "Mật khẩu mới không khớp";
							return; // Dừng lại nếu có lỗi
						}

						// Submit form nếu dữ liệu hợp lệ
						event.target.submit();
					});
</script>
</html>

