<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<div class="container mt-5">
	<div class="row">
		<div class="product-aside col-3 mt-2">
			<jsp:include page="components/product-aside.jsp"></jsp:include>
		</div>
		<div class=" col-9">
			<jsp:include page="components/product-show.jsp"></jsp:include>
		</div>

	</div>
</div>


<jsp:include page="components/home-whyUs.jsp"></jsp:include>

<jsp:include page="components/home-client.jsp"></jsp:include>