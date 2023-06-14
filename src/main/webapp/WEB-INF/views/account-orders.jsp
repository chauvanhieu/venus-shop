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
			<table class="table">
				<thead class="thead-light">
					<tr>
						<th scope="col">Mã</th>
						<th scope="col">Khách hàng</th>
						<th scope="col">Ngày đặt</th>
						<th scope="col">Tổng tiền</th>
						<th scope="col">Trạng thái</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${listOrder }">
						<tr>
							<th scope="row">${item.id }</th>
							<td style="color: blue; font-weight: bold">${item.user.fullName }</td>
							<td>${item.createdAt }</td>
							<td><fmt:formatNumber value="${item.amount}"
									pattern="###,###" /></td>
							<td><c:choose>
									<c:when test="${item.status==1 }">
										<span style="color: green; font-weight: bold">Đã thanh
											toán</span>
									</c:when>
									<c:when test="${item.status==2 }">
										<span style="color: red; font-weight: bold">Đã hủy</span>
									</c:when>
									<c:otherwise>
										<span style="color: red; font-weight: bold">Chưa thanh
											toán</span>
									</c:otherwise>
								</c:choose></td>
							<td><a href="/account/orders/${item.id }">
									<button class="btn btn-primary">Xem</button>
							</a></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>