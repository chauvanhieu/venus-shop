<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- product section -->
<style>
.product-show {
	height: 70vh; /* Định dạng chiều cao của thẻ div */
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
<section class="product_section">
	<div class="container product-show">

		<div class="row">
			<c:forEach var="product" items="${pageProduct.content }">


				<div class="col-sm-6 col-lg-4">
					<div class="box">
						<div class="img-box">
							<img src="http://localhost:8080/images/p1.png" alt=""> <a
								href="/shopping-cart/add/${product.id }" class="add_cart_btn">
								<span> <s:message code="web.card.add-to-cart" />
							</span>
							</a>
						</div>
						<div class="detail-box">
							<a href="/product/${product.id }" style="color: unset">
								<h5>${product.name }</h5>
							</a>

							<div class="product_info">
								<h5>
									<fmt:formatNumber value="${product.price}" pattern="###,###" />
								</h5>
								<div class="star_container">
									<i class="fa fa-star" aria-hidden="true"></i> <i
										class="fa fa-star" aria-hidden="true"></i> <i
										class="fa fa-star" aria-hidden="true"></i> <i
										class="fa fa-star" aria-hidden="true"></i> <i
										class="fa fa-star" aria-hidden="true"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>

<nav aria-label="Page navigation example "
	style="width: max-content; margin: 50px auto">
	<ul class="pagination">
		<li class="page-item"><a class="page-link"
			href="/product?keyword=${param.keyword }&range=${param.range }&category_id=${param.category_id }&sort_by=${param.sort_by }&order_by=${param.order_by }&page=0&limit=${param.limit}">First</a></li>
		<li class="page-item"><a class="page-link"
			href="/product?keyword=${param.keyword }&range=${param.range }&category_id=${param.category_id }&sort_by=${param.sort_by }&order_by=${param.order_by }&page=${pageProduct.number-1 }&limit=${param.limit}">Previous</a></li>
		<li class="page-item"><a class="page-link" href="#">${pageProduct.number+1 }
				/ ${pageProduct.totalPages }</a></li>
		<c:if test="${(pageProduct.number+1)<pageProduct.totalPages }">
			<li class="page-item"><a class="page-link"
				href="/product?keyword=${param.keyword }&range=${param.range }&category_id=${param.category_id }&sort_by=${param.sort_by }&order_by=${param.order_by }&page=${pageProduct.number+1 }&limit=${param.limit}">Next</a></li>
			<li class="page-item"><a class="page-link"
				href="/product?keyword=${param.keyword }&range=${param.range }&category_id=${param.category_id }&sort_by=${param.sort_by }&order_by=${param.order_by }&page=${pageProduct.totalPages-1 }&limit=${param.limit}">Last</a></li>
		</c:if>

	</ul>
</nav>
</section>


<!-- end product section -->