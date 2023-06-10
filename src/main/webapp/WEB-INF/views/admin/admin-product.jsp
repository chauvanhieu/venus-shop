<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib
	prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


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
<div class="container-fluid admin-product row">
	<div class="admin-product-control col-2">
		<form action="/admin/product" method="get">
			<div class="form-group">
				<input class="form-control" name="keyword"
					placeholder="Tìm kiếm..." value="${param.keyword }">
			</div>
			<div class="form-group">
				<label>Khoảng giá:</label> <select class="form-control"
					name="range">
					<option value="mọi khoảng giá"
						${param.range == 'mọi khoảng giá' ? 'selected' : '' }>Mọi
						khoảng giá</option>
					<option value="dưới 5 triệu"
						${param.range == 'dưới 5 triệu' ? 'selected' : '' }>Dưới
						5 triệu</option>
					<option value="5 đến 10 triệu"
						${param.range == '5 đến 10 triệu' ? 'selected' : '' }>5
						- 10 triệu</option>
					<option value="10 đến 20 triệu"
						${param.range == '10 đến 20 triệu' ? 'selected' : '' }>10
						- 20 triệu</option>
					<option value="20 đến 30 triệu"
						${param.range == '20 đến 30 triệu' ? 'selected' : '' }>20
						- 30 triệu</option>
					<option value="trên 30 triệu"
						${param.range == 'trên 30 triệu' ? 'selected' : '' }>Trên
						30 triệu</option>
				</select>
			</div>
			<div class="form-group">
				<label>Danh mục:</label> <select class="form-control"
					name="category_id">
					<option value="0">Tất cả</option>
					<c:forEach var="category" items="${listCategory }">
						<option value="${category.id }"
							${category.id==param.category_id ? 'selected' : '' }>${category.name }</option>
					</c:forEach>

				</select>
			</div>

			<div class="form-group">
				<label>Sắp xếp theo:</label><br /> <input
					${param.sort_by == 'name' ? 'checked' : '' } type="radio"
					value="name" name="sort_by"><label>Tên</label><br /> <input
					${param.sort_by == 'price' ? 'checked' : '' } type="radio"
					value="price" name="sort_by"><label>Giá</label><br /> <input
					${param.sort_by == 'createdAt' ? 'checked' : '' } type="radio"
					value="createdAt" name="sort_by"><label>Thời gian
					tạo</label><br />
			</div>
			<div class="form-group">
				<select class="form-control" name="order_by">
					<option value="ASC" ${param.order_by == 'ASC' ? 'selected' : '' }>Tăng
						dần</option>
					<option value="DESC" ${param.order_by == 'DESC' ? 'selected' : '' }>Giảm
						dần</option>
				</select>

			</div>
			<button class="btn btn-success">Tìm kiếm</button>
		</form>
	</div>

	<div class="admin-product-table col-10">
		<c:forEach var="item" items="${listProduct }">
			<div class="product row mb-3"
				style="background-color: #ffffff; margin: 0 auto; padding: 10px">
				<div class="product-image col-2">
					<img alt="product-name" class="img-thumbnail" height="100"
						src="https://cdn2.cellphones.com.vn/x358,webp,q100/media/catalog/product/1/1/11h50.png">
				</div>
				<div class="product-info col-7">
					<h3>${item.name }</h3>
					<div class="star_container" style="color: #f5c451;">
						<i class="fa fa-star" aria-hidden="true"></i> <i
							class="fa fa-star" aria-hidden="true"></i> <i class="fa fa-star"
							aria-hidden="true"></i> <i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
					</div>
					<p class="text-justify">${item.createdAt }</p>
					<p class="text-justify">Danh mục: ${item.category.name }</p>
					<p class="text-justify">Có sẵn: ${item.available }</p>
				</div>
				<div class="product-buttons col-3">
					<h5 style="color: red">
						Giá bán:
						<fmt:formatNumber value="${item.price}" pattern="###,###" />
					</h5>
					<div class="button-group">
						<form action="/admin/product/update-status" method="post">
							<input name="id" type="hidden" value="${item.id }">
							<button class="btn btn-success">Chi tiết</button>
							<br /> <br /> <label>Đăng bán</label> <label class="switch">
								<input onchange="this.form.submit()" type="checkbox"
								name="status" ${item.status==1 ? 'checked':'' }> <span
								class="slider round"></span>
							</label><br /> <br />

						</form>

						<form action="/admin/product/update-issale" method="post">
							<input name="id" type="hidden" value="${item.id }"> <label>Giảm
								giá </label> <label class="switch"> <input
								onchange="this.form.submit()" type="checkbox" name="isSale"
								${item.isSale==1 ? 'checked':'' }> <span
								class="slider round"></span>
							</label>
						</form>
					</div>
				</div>
			</div>
		</c:forEach>

	</div>
</div>