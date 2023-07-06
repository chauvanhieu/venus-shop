<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="container" style="margin-top: 30px">
	<div class="row">
		<div class="account-aside col-3">
			<ul class="list-group">
				<a href="/account/information"><li class="list-group-item ">Thông
						tin tài khoản</li></a>

				<a href="/account/orders"><li class="list-group-item active">Đơn
						hàng đã giao dịch</li></a>


				<a href="/account/password "><li class="list-group-item ">Đổi
						mật khẩu</li></a>
			</ul>
		</div>
		<div class="account-body col-9">
			<h4>Đơn hàng: ${accountOrderDetail.id }</h4>
			<p>
				<c:choose>
					<c:when test="${accountOrderDetail.status==1 }">
						<span style="color: green;font-weight: bold">Đã thanh toán</span>
					</c:when>
					<c:when test="${accountOrderDetail.status==2 }">
						<span style="color: red;font-weight: bold">Đơn hàng đã hủy</span>
						<a href="/account/orders/restore/${accountOrderDetail.id }">
							<button  class="btn btn-primary float-right">Khôi phục</button>
						</a>
					</c:when>
					<c:otherwise>
						<span style="color: red;font-weight: bold">Chờ thanh toán</span>
						<a href="/account/orders/cancel/${accountOrderDetail.id }">
							<button   class="btn btn-danger float-right">Hủy đơn</button>
						</a>
					</c:otherwise>
				</c:choose>
			</p>
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
					<c:forEach var="item" items="${accountOrderDetail.orderDetails }">
						<form action="/shopping-cart/update/${item.id }">

							<tr>
								<th><img alt="${item.product.image }" class="img-thumbnail"
									src="${item.product.image }" style="width: 200px"></th>
								<td>${item.product.name }</td>
								<td><fmt:formatNumber value="${item.product.price}"
										pattern="###,###" /></td>
								<td><input disabled="disabled" style="width: 70px"
									onblur="this.form.submit()" name="quantity"
									value="${item.count }" type="number" /></td>
								<td><fmt:formatNumber
										value="${item.product.price * item.count}" pattern="###,###" /></td>
							</tr>
						</form>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
</div>