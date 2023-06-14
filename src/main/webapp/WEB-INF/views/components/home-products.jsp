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
				<s:message code="web.our-products" />
			</h2>
		</div>
		<div class="row">

			<c:forEach var="product" items="${listProduct }">


				<div class="col-sm-6 col-lg-4">
					<div class="box">
						<div class="img-box">
							<img src="${product.image }" alt=""> <a
								href="/shopping-cart/add/${product.id }" class="add_cart_btn"> <span> <s:message
										code="web.card.add-to-cart" />
							</span>
							</a>
						</div>
						<div class="detail-box">
							<a href="/product/${product.id }" style="color: unset">
								<h5>${product.name }</h5>
							</a>

							<div class="product_info">
								<h5><fmt:formatNumber value="${product.price}" pattern="###,###" /></h5>
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
		<div class="btn_box">
			<a href="/product" class="view_more-link"><s:message
					code="web.our-products.view-more" /></a>
		</div>
	</div>
</section>

<!-- end product section -->