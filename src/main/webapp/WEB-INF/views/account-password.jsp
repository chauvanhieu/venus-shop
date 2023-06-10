<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container" style="margin-top: 30px">
	<div class="row">
		<div class="account-aside col-3">
			<ul class="list-group">
				<a href="/account/information"><li class="list-group-item ">Thông
						tin tài khoản</li></a>

				<a href="/account/orders"><li class="list-group-item ">Đơn
						hàng đã giao dịch</li></a>


				<a href="/account/password"><li class="list-group-item active">Đổi
						mật khẩu</li></a>
			</ul>
		</div>
		<div class="account-body col-5">
			<form action="/account/password" method="post"
				name="form-set-password" onsubmit="return validateForm()">
				<div class="form-group">
					<p style="color:red">${param.resetPasswordError }</p>
				</div>
				<div class="form-group">
					<label>Nhập mật khẩu của bạn:</label> <input type="password"
						class="form-control" name="old-password" id="old-password" />
				</div>
				<div class="form-group">
					<label>Nhập mật khẩu mới:</label> <input type="password"
						class="form-control" name="new-password" id="new-password" />
				</div>
				<div class="form-group">
					<label>Nhập lại mật khẩu mới:</label> <input type="password"
						class="form-control" name="confirm-new-password"
						id="confirm-new-password" />
				</div>
				<button class="btn btn-success">Đổi mật khẩu</button>
			</form>
		</div>
	</div>
</div>

<script>
	function validateForm() {
		var oldPassword = document.getElementById("old-password").value;
		var newPassword = document.getElementById("new-password").value;
		var confirmNewPassword = document
				.getElementById("confirm-new-password").value;

		if (oldPassword === "") {
			alert("Vui lòng nhập mật khẩu cũ");
			return false;
		}

		if (newPassword === "") {
			alert("Vui lòng nhập mật khẩu mới");
			return false;
		}

		if (confirmNewPassword === "") {
			alert("Vui lòng nhập lại mật khẩu mới");
			return false;
		}

		if (newPassword !== confirmNewPassword) {
			alert("Mật khẩu mới và xác nhận mật khẩu không khớp");
			return false;
		}

		

		return true;
	}
</script>
