<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>

<jsp:include page="head.jsp"></jsp:include>
<style>
.admin-body_main {
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

</head>
<body>
	<div id="root" style="background-color: #d7d8dd !important">


		<div class="container-fluid" style="padding: 0; margin: 0">
			<div class="row">
				<div class="admin-aside col-2"
					style="background-color: #3a4468; height: 100vh; padding-right: 0 !important">

					<tiles:insertAttribute name="aside" />


				</div>
				<div class="admin-body col-10" style="padding: 0; margin: 0">
					<div class="admin-body_header"
						style="height: 10vh; text-align: right">
						<tiles:insertAttribute name="header" />
					</div>
					<div class="admin-body_main" style="height: 90vh">
						<tiles:insertAttribute name="main" />
					</div>
				</div>
			</div>
		</div>


	</div>
</body>
</html>