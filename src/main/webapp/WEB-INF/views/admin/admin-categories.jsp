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
</style>
<div class="container-fluid admin-categories">
	<div class="row container">
		<div>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary mb-2"
				data-toggle="modal" data-target="#exampleModalCenter">Tạo
				danh mục</button>

			<!-- Modal -->
			<div class="modal fade" id="exampleModalCenter" tabindex="-1"
				role="dialog" aria-labelledby="exampleModalCenterTitle"
				aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLongTitle">Thêm danh
								mục</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<form action="/admin/category/add" method="post">
								<div class="form-group">
									<label>Nhập tên danh mục</label> <input class="form-control"
										name="name">
								</div>
								<button class="btn btn-success">Tạo mới</button>
								<button type="button" class="btn btn-secondary"
									data-dismiss="modal">Đóng</button>
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>
		<div>
			<p style="color: red; margin: 10px">${param.error == true ? 'Tên danh mục không được để trống':'' }</p>
		</div>
	</div>


	<div class="admin-categories-table" style="width: 1000px">
		<table class="table">
			<thead class="thead-light">
				<tr>
					<th scope="col">Mã</th>
					<th scope="col">Danh mục sản phẩm</th>
					<th scope="col">Trạng thái</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${listCategories }">
					<form action="/admin/category/update" method="post">
						<input name="id" type="hidden" value="${item.id }" />
						<tr>
							<th scope="row">${item.id }</th>
							<td><input class="form-control" onblur="checkAndSubmit(this)" name="name"
								type="text" value="${item.name }" /></td>
							<td><label class="switch"> <input
									onchange="this.form.submit()" type="checkbox" name="status"
									${item.status==1 ? 'checked':'' }> <span
									class="slider round"></span>
							</label></td>

							<td><a href="/admin/product?category_id=${item.id }">Xem
									sản phẩm</a></td>

						</tr>
					</form>
				</c:forEach>

			</tbody>
		</table>
	</div>
</div>
