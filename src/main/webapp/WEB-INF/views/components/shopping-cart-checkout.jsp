<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		<label>Tổng tiền đơn hàng : <span
			style="color: red; font-weight: bold"> <fmt:formatNumber
					value="${amount}" pattern="###,###" /> VNĐ
		</span></label>
	</div>
	<div class="form-group">
		<label>Nhắc nhở đến shop: </label>
		<textarea rows="4" class="form-control" name="order_note"></textarea>
	</div>

	<button class="btn btn-success" ${amount<1 ? 'disabled="disabled"':'' }>Xác
		nhận đơn</button>
	<a href="/product">
		<button type="button" class="btn btn-info">Tiếp tục mua
			hàng</button>
	</a>
</form>