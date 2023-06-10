<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="table">
	<thead class="thead-light">
		<tr>
			<th scope="col">Hình ảnh</th>
			<th scope="col">Sản phẩm</th>
			<th scope="col">Giá</th>
			<th scope="col">Số lượng</th>
			<th scope="col">Thành tiền</th>
			<th scope="col"></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="item" items="${ShoppingCart }">
		<form action="/shopping-cart/update/${item.id }">
		
			<tr>
				<th><img alt="${item.image }" src="${item.image }"
					style="width: 200px"></th>
				<td>${item.name }</td>
				<td><fmt:formatNumber value="${item.price}" pattern="###,###" /></td>
				<td>
					<input style="width: 70px" onblur="this.form.submit()" name="quantity" value="${item.quantity }" type="number"/>
				</td>
				<td><fmt:formatNumber value="${item.price * item.quantity}"
						pattern="###,###" /></td>


				<td style="cursor: pointer;"><a
					href="/shopping-cart/remove/${item.id }"><i
						class="fa-solid fa-trash-can fa-2xl" style="color: #bb1637;"></i></a></td>

			</tr>
		</form>
		</c:forEach>

	</tbody>
</table>
