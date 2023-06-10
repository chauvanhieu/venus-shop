<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="head.jsp"></jsp:include>

</head>
<body>
	<div id="root">
	
		<tiles:insertAttribute name="header" />	
		
		<tiles:insertAttribute name="body" />	
		
		<tiles:insertAttribute name="footer" />
		
	</div>
</body>
</html>