<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.orders-table {
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
<div class="container-fluid admin-order">
	<div class="row">
		<div class="orders-aside col-2 mt-1">
			<form action="/admin/order" method="get">
				<div class="form-group">
					<input class="form-control" name="keyword" type="number"
						placeholder="Tìm kiếm..." value="${param.keyword }">
				</div>

				<div class="form-group">
					<label>Sắp xếp theo:</label><br /> <input
						${param.sort_by == 'id' ? 'checked' : '' } type="radio" value="id"
						name="sort_by"><label>Mã</label><br /> <input
						${param.sort_by == 'createdAt' ? 'checked' : '' } type="radio"
						value="createdAt" name="sort_by"><label>Thời gian</label><br />
					<input ${param.sort_by == 'status' ? 'checked' : '' } type="radio"
						value="status" name="sort_by"><label>Trạng thái</label><br />

					<input ${param.sort_by == 'amount' ? 'checked' : '' } type="radio"
						value="amount" name="sort_by"><label>Tổng tiền</label><br />
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


		<div class="orders-table col-10">
			<table class="table">
				<thead class="thead-light">
					<tr>
						<th scope="col">Mã</th>
						<th scope="col">Khách hàng</th>
						<th scope="col">Ngày đặt</th>
						<th scope="col">Ghi chú</th>
						<th scope="col">Tổng tiền</th>
						<th scope="col">Trạng thái</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${listOrder }">
						<tr>
							<th scope="row">${item.id }</th>
							<td  style="color: blue; font-weight: bold">${item.user.fullName }</td>
							<td>${item.createdAt }</td>
							<td><c:out value="${fn:substring(item.note, 0, 40)}" />...</td>
							<td><fmt:formatNumber value="${item.amount}"
									pattern="###,###" /></td>
							<td><c:choose>
									<c:when test="${item.status==1 }">
										<span style="color: green;font-weight: bold">Đã thanh toán</span>
									</c:when>
									<c:when test="${item.status==2 }">
										<span style="color: red;font-weight: bold">Đã hủy</span>
									</c:when>
									<c:otherwise>
										<span style="color: red;font-weight: bold">Chưa thanh toán</span>
									</c:otherwise>
								</c:choose></td>
							<td><a href="/admin/order/${item.id }">
									<button class="btn btn-primary">Xem</button>
							</a></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>