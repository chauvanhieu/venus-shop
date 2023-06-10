<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-fluid" style="min-height: 78vh">
	<div class="heading_container heading_center m-3">
		<h2>
			Giỏ hàng của bạn
		</h2>
	</div>
	<div class="row">
		<div class="shoppingcart-products col-7">
			<jsp:include page="components/shopping-cart-products.jsp"></jsp:include>
		</div>
		<div class="shoppingcart-checkout col-4">
			<jsp:include page="components/shopping-cart-checkout.jsp"></jsp:include>
		</div>
	</div>
</div>


<jsp:include page="components/home-whyUs.jsp"></jsp:include>

<jsp:include page="components/home-client.jsp"></jsp:include>
