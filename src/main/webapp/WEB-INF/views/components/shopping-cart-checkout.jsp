<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form method="post">
	<input type="hidden" name="user_id">
	<div class="form-group">
		<label>Tên người mua</label>
		<h3>${user.fullName }</h3>
	</div>
	<div class="form-group">
		<label>Số điện thoại: ${user.phoneNumber }</label>
	</div>
	<div class="form-group">
		<label>Email: ${user.email }</label>
	</div>
	<div class="form-group">
		<label>Địa chỉ: ${user.address }</label>
	</div>
	<div class="form-group">
		<label>Nhắc nhở đến shop: </label>
		<textarea rows="4" class="form-control" name="order_note"></textarea>
	</div>

	<button class="btn btn-success">Xác nhận đơn</button>
	<a href="/product">
		<button type="button" class="btn btn-info">Tiếp tục mua
			hàng</button>
	</a>
</form>