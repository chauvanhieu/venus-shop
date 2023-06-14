<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

.admin-product-table {
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
<div class="container-fluid admin-customer-single">
	<div class="container customer-infomation" style="max-width: 700px">
		<div class="form-group">
			<label>Tên khách hàng</label> <input class="form-control"
				type="text" disabled="disabled" value="${customer.fullName }">
		</div>
		<div class="form-group">
			<label>Số điện thoại</label> <input class="form-control"
				type="text" disabled="disabled" value="${customer.phoneNumber }">
		</div>
		<div class="form-group">
			<label>Địa chỉ</label> <input class="form-control" type="text"
				disabled="disabled" value="${customer.address }">
		</div>
		<div class="form-group">
			<label>Email</label> <input class="form-control" type="text"
				disabled="disabled" value="${customer.email }">
		</div>
		<div class="form-group">
			<label>Trạng thái</label></br> <label class="switch"> <input
				class="form-control" onchange="this.form.submit()" type="checkbox"
				name="status" ${customer.status==1 ? 'checked':'' }> <span
				class="slider round"></span>
			</label>
		</div>
	</div>

	<div class="container customer-orders">
		<h5>Các đơn hàng đã đặt</h5>
		<table class="table">
			<thead class="thead-light">
				<tr>
					<th scope="col">Mã đơn hàng</th>
					<th scope="col">Ngày đặt</th>
					<th scope="col">Ghi chú</th>
					<th scope="col">Tổng tiền</th>
					<th scope="col">Trạng thái</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${listOrderForUser }">
					<tr>
						<th scope="row">${item.id }</th>
						<td>${item.createdAt }</td>
						<td>${item.note }</td>
						<td><fmt:formatNumber value="${item.amount}"
								pattern="###,###" /></td>
						<td><c:choose>
								<c:when test="${item.status==1 }">
									<span style="color: green">Đã thanh toán</span>
								</c:when>
								<c:otherwise>
									<span style="color: green">Chưa thanh toán</span>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>