<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3>Tìm kiếm sản phẩm</h3>
<form method="get">
	<div class="form-group">
		<input class="form-control" name="keyword" placeholder="Tìm kiếm..."
			value="${param.keyword }" />
	</div>
	<div class="form-group">
		<label>Khoảng giá:</label> <select class="form-control" name="range">
			<option value="mọi khoảng giá"
				${param.range == 'mọi khoảng giá' ? 'selected' : '' }>Mọi
				khoảng giá</option>
			<option value="dưới 5 triệu"
				${param.range == 'dưới 5 triệu' ? 'selected' : '' }>Dưới
				5 triệu</option>
			<option value="5 đến 10 triệu"
				${param.range == '5 đến 10 triệu' ? 'selected' : '' }>5 -
				10 triệu</option>
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