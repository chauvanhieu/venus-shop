<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="container-fluid">
	<h1>Danh sách sản phẩm bán chạy nhất</h1>
	<table class="table">
		<thead class="thead-light">
			<tr>
				<th scope="col">Mã</th>
				<th scope="col">Ảnh</th>
				<th scope="col">Tên sản phẩm</th>
				<th scope="col">Giá bán</th>
				<th scope="col">Doanh số</th>
				<th scope="col">Doanh thu</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${reportData }">
				<tr>
					<th scope="row">${item.group }</th>
					<td><img width="100px" class="img-thumbnail"
						src="${item.image }"></td>
					<td><a href="/admin/product/edit/${item.group }">${item.name }</a></td>
					<td><fmt:formatNumber value="${item.price}" pattern="###,###" /></td>
					<td>${item.count }</td>
					<td><fmt:formatNumber value="${item.amount}" pattern="###,###" /></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</div>
