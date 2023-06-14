<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
.switch {
	position: relative;
	display: inline-block;
	width: 60px;
	height: 34px;
}

.switch input {
	opacity: 0;
	width: 0;
	height: 0;
}

.slider {
	position: absolute;
	cursor: pointer;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: #ccc;
	-webkit-transition: .4s;
	transition: .4s;
}

.slider:before {
	position: absolute;
	content: "";
	height: 26px;
	width: 26px;
	left: 4px;
	bottom: 4px;
	background-color: white;
	-webkit-transition: .4s;
	transition: .4s;
}

input:checked+.slider {
	background-color: #2196F3;
}

input:focus+.slider {
	box-shadow: 0 0 1px #2196F3;
}

input:checked+.slider:before {
	-webkit-transform: translateX(26px);
	-ms-transform: translateX(26px);
	transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
	border-radius: 34px;
}

.slider.round:before {
	border-radius: 50%;
}

.shoppingcart-products {
	height: 87vh; /* Định dạng chiều cao của thẻ div */
	overflow-y: scroll; /* Thêm thanh cuộn */
}
/* width */
::-webkit-scrollbar {
	width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
	background: #f1f1f1;
}

/* Handle */
::-webkit-scrollbar-thumb {
	background: #888;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
	background: #555;
}
</style>

<div class="container-fluid" style="min-height: 78vh">
	<div class="row">
		<div class="shoppingcart-products col-7">
			<table class="table">
				<thead class="thead-light">
					<tr>
						<th scope="col">Hình ảnh</th>
						<th scope="col">Sản phẩm</th>
						<th scope="col">Giá</th>
						<th scope="col">Số lượng</th>
						<th scope="col">Thành tiền</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${order.orderDetails }">
						<tr>
							<th><img alt="${item.product.image }" class="img-thumbnail"
								src="${item.product.image }" style="width: 200px"></th>
							<td>${item.product.name }</td>
							<td><fmt:formatNumber value="${item.product.price}"
									pattern="###,###" /></td>
							<td><input style="width: 70px" name="quantity"
								value="${item.count }" type="number" disabled="disabled" /></td>
							<td><fmt:formatNumber
									value="${item.product.price * item.count}" pattern="###,###" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="shoppingcart-checkout col-4">
			<div class="form-group">
				<h3>${order.user.fullName }</h3>
			</div>
			<div class="form-group">
				<p>Số điện thoại: ${order.user.phoneNumber }</p>
			</div>
			<div class="form-group">
				<p>Email: ${order.user.email }</p>
			</div>
			<div class="form-group">
				<p>Địa chỉ: ${order.user.address }</p>
			</div>

			<div class="form-group">
				<p>
					Tổng hóa đơn: <span style="color: red; font-weight: bold"><fmt:formatNumber
							value="${order.amount}" pattern="###,###" /> VNĐ</span>
				</p>
			</div>
			<div class="form-group">
				<label>Nội dung hóa đơn:</label>
				<textarea disabled="disabled" class="form-control" rows="4">${order.note }</textarea>
			</div>
			<c:if test="${order.status!=2 }">
				<form action="/admin/order/confirm" method="post">
					<input value="${order.id }" name="id" type="hidden">
					<div class="form-group">
						<label>Xác nhận đã thanh toán đơn hàng:</label> <label
							class="switch"> <input onchange="this.form.submit()"
							type="checkbox" name="status" ${order.status==1 ? 'checked':'' }>
							<span class="slider round"></span>
						</label>
					</div>
				</form>
			</c:if>
			<c:if test="${order.status==2 }">
				<p style="color:red">Đơn hàng đã hủy</p>
			</c:if>
		</div>
	</div>
</div>