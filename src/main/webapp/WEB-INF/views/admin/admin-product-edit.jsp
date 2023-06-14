<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ taglib
	prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
.error {
	color: red
}

.switch {
	position: relative;
	display: inline-block;
	width: 60px;
	height: 34px;
}

.switch input {
	opacity: 0;
	width: 0;
	height: 0;
}

.slider {
	position: absolute;
	cursor: pointer;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background-color: #ccc;
	-webkit-transition: .4s;
	transition: .4s;
}

.slider:before {
	position: absolute;
	content: "";
	height: 26px;
	width: 26px;
	left: 4px;
	bottom: 4px;
	background-color: white;
	-webkit-transition: .4s;
	transition: .4s;
}

input:checked+.slider {
	background-color: #2196F3;
}

input:focus+.slider {
	box-shadow: 0 0 1px #2196F3;
}

input:checked+.slider:before {
	-webkit-transform: translateX(26px);
	-ms-transform: translateX(26px);
	transform: translateX(26px);
}

/* Rounded sliders */
.slider.round {
	border-radius: 34px;
}

.slider.round:before {
	border-radius: 50%;
}
</style>
<div class="container">
	<div class="form-container">
		<form:form class="form" modelAttribute="productAttribute"
			method="post" action="/admin/product/edit" enctype="multipart/form-data">
			<div class="row">
				<div class="col-6">
					<div class="form-group">
						<form:label path="name">Tên sản phẩm</form:label>
						<form:input path="name" class="form-control" />
						<form:errors class="error" path="name" />
					</div>
					<div class="form-group">
						<form:label path="category.id">Danh mục</form:label>
						<form:select path="category" class="form-control">
							<form:options items="${listCategory }" itemLabel="name"
								itemValue="id" />
						</form:select>
					</div>
					<div class="form-group">
						<form:label path="price">Giá bán</form:label>
						<form:input path="price" type="number" class="form-control" />
						<form:errors class="error" path="price" />
					</div>
					<div class="row">
						<div class="form-group col-6">
							<form:label path="available">Tồn kho</form:label>
							<form:input path="available" type="number" class="form-control" />
							<form:errors class="error" path="available" />
						</div>
						<div class="form-group col-6">
							<label>Ảnh sản phẩm</label> <input
								placeholder="Chọn hình ảnh sản phẩm" id="imageInput"
								accept="image/*" class="form-control" name="img" type="file">

							<form:input path="image" type="hidden" />
						</div>
					</div>
					<div class="form-group">
						<label>Trạng thái đăng bán</label> <br /> <label
							class="switch"> <input type="checkbox" name="stt"
							${productAttribute.status==1 ? 'checked':'' }> <span
							class="slider round"></span>
						</label>
					</div>
					<form:input path="description" type="hidden" />
					<form:input path="id" type="hidden" />
					<form:input path="createdAt" type="hidden" />
				</div>
				<div class="col-6 text-center">
					<img id="imageElement" alt="" class="img-fluid"
						style="max-height: 100%" src="${productAttribute.image }">
				</div>

			</div>

			<div class="form-group">
				<label>Mô tả</label>

				<textarea name="desc" class="form-control">${productAttribute.description }</textarea>
			</div>
			<button class="btn btn-success">Tạo mới</button>
		</form:form>
	</div>
</div>

<script src="/ckeditor/ckeditor.js"></script>
<script>
	CKEDITOR.replace('desc');

	// Lấy đối tượng input từ HTML dựa vào id
	var imageInput = document.getElementById('imageInput');

	// Lắng nghe sự kiện 'change' khi người dùng chọn một file
	imageInput.addEventListener('change', function(event) {
		var file = event.target.files[0]; // Lấy file đầu tiên trong danh sách các file đã chọn

		// Kiểm tra xem người dùng đã chọn file hay chưa
		if (file) {
			var reader = new FileReader(); // Tạo đối tượng FileReader để đọc nội dung của file

			// Xử lý sự kiện 'load' khi FileReader hoàn thành việc đọc file
			reader.addEventListener('load', function() {
				var imageURL = reader.result; // Lấy URL hình ảnh đã đọc từ file

				var imageElement = document.getElementById('imageElement');
				imageElement.src = imageURL; // Gán URL hình ảnh cho thuộc tính 'src' của thẻ 'img'
			});

			reader.readAsDataURL(file); // Đọc file dưới dạng URL dữ liệu (data URL)
		}
	});
</script>