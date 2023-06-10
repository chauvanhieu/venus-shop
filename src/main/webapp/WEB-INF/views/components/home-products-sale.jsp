<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- product section -->

<section class="product_section layout_padding">
	<div class="container">
		<div class="heading_container heading_center">
			<h2>
				<s:message code="web.our-products-sale" />
			</h2>
		</div>
		<div class="row">

			<c:forEach var="product" items="${productSaling }">


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
</section>

<!-- end product section -->