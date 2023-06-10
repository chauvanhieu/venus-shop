<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container" style="margin-top: 30px">
	<div class="row">
		<div class="account-aside col-3">
			<ul class="list-group">
				<a href="/account/information"><li
					class="list-group-item active">Thông tin tài khoản</li></a>

				<a href="/account/orders"><li class="list-group-item ">Đơn
						hàng đã giao dịch</li></a>


				<a href="/account/password"><li class="list-group-item ">Đổi
						mật khẩu</li></a>
			</ul>
		</div>
		<div class="account-body col-9">
			<form:form class="form" modelAttribute="userAttribute" method="post">
				<div class="form-group">
					<form:label path="fullName">Tên chủ tài khoản:</form:label>
					<form:input onchange="enableButtonUpdate()" class="form-control"
						path="fullName" />
					<form:errors class="error" path="fullName" />
				</div>
				<div class="form-group">
					<form:label path="email">Địa chỉ email:</form:label>
					<form:input onchange="enableButtonUpdate()" class="form-control"
						path="email" />
					<form:errors class="error" path="email" />
				</div>
				<div class="form-group">
					<form:label path="phoneNumber">Số điện thoại:</form:label>
					<form:input onchange="enableButtonUpdate()" class="form-control"
						path="phoneNumber" />
					<form:errors class="error" path="phoneNumber" />
				</div>
				<div class="form-group">
					<form:label path="address">Địa chỉ:</form:label>
					<form:input onchange="enableButtonUpdate()" class="form-control"
						path="address" />
					<form:errors class="error" path="address" />
				</div>
				<form:input path="password" type="hidden" />
				<button class="btn btn-secondary" id="btn-account-update"
					disabled="disabled">Cập nhật</button>
			</form:form>
		</div>
	</div>
</div>
<style>
.error {
	color: red
}
</style>
<script>
	function enableButtonUpdate() {
		let btnUpdate = document.querySelector('#btn-account-update')
		btnUpdate.disabled = false;
		btnUpdate.classList.remove("btn-secondary");
		btnUpdate.classList.add("btn-success");
	}
</script>