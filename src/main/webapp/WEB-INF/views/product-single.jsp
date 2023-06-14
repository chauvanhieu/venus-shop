<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container mt-3">
	<div class="product-infomation row">
		<div class="product-infomation_image col-5">
			<jsp:include page="components/product-single-image.jsp"></jsp:include>
		</div>
		<div class="product-infomation_info col-7">
			<jsp:include page="components/product-single-info.jsp"></jsp:include>
		</div>
	</div>
	<div class="product-description mt-5" style="width: 900px">
		<c:out value="${product.description}" escapeXml="false" />
	</div>
</div>


<jsp:include page="components/home-whyUs.jsp"></jsp:include>

<jsp:include page="components/home-client.jsp"></jsp:include>