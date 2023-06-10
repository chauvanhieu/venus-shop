<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h1>${product.name }</h1>
<p>
	Tặng chuột không dây trị giá <span style="color: red">500.000đ</span>
	khi thanh toán online.
</p>
<h5>
	Giá:
	<fmt:formatNumber value="${product.price}" pattern="###,###" />
	vnđ
</h5>
<a href="/shopping-cart/add/${product.id }">
	<button class="btn btn-primary m-1">Thêm vào giỏ hàng</button>
</a>
<img src="/images/sale.png" style="width: 100%" />