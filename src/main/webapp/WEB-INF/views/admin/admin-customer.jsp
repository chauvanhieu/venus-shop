<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<div class="container-fluid ">
	<div class="row">
		<div class="customer-aside col-2 mt-1">
			<form action="/admin/customer" method="get">
				<div class="form-group">
					<input class="form-control" name="keyword"
						placeholder="Tìm kiếm..." value="${param.keyword }">
				</div>

				<div class="form-group">
					<label>Sắp xếp theo:</label><br /> <input
						${param.sort_by == 'id' ? 'checked' : '' } type="radio"
						value="id" name="sort_by"><label>Mã</label><br /> <input
						${param.sort_by == 'fullName' ? 'checked' : '' } type="radio"
						value="fullName" name="sort_by"><label>Tên</label><br /> <input
						${param.sort_by == 'status' ? 'checked' : '' } type="radio"
						value="status" name="sort_by"><label>Trạng
						thái</label><br />
				</div>
				<div class="form-group">
					<select class="form-control" name="order_by">
						<option value="ASC" ${param.order_by == 'ASC' ? 'selected' : '' }>Tăng
							dần</option>
						<option value="DESC"
							${param.order_by == 'DESC' ? 'selected' : '' }>Giảm
							dần</option>
					</select>

				</div>
				<button class="btn btn-success">Tìm kiếm</button>
			</form>
		</div>
		<div class="customer-table col-10">
			<table class="table">
				<thead class="thead-light">
					<tr>
						<th scope="col">Mã</th>
						<th scope="col">Tên</th>
						<th scope="col">Email</th>
						<th scope="col">Địa chỉ</th>
						<th scope="col">Số điện thoại</th>
						<th scope="col">Trạng thái</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${listCustomer }">
						<form action="/admin/customer/update" method="post">
							<input name="id" value="${item.id }" type="hidden">
							<tr>
								<th scope="row">${item.id }</th>
								<td><a href="/admin/customer/${item.id }">${item.fullName }</a></td>
								<td>${item.email }</td>
								<td>${item.address }</td>
								<td>${item.phoneNumber }</td>
								<td><label class="switch"> <input
										onchange="this.form.submit()" type="checkbox" name="status"
										${item.status==1 ? 'checked':'' }> <span
										class="slider round"></span>
								</label></td>
							</tr>
						</form>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
</div>
