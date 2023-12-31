<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to VenusShop</title>
<style type="text/css">
*, *:before, *:after {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

body {
	font-family: 'Open Sans', Helvetica, Arial, sans-serif;
	background: #ffffff;
}

input, button {
	border: none;
	outline: none;
	background: none;
	font-family: 'Open Sans', Helvetica, Arial, sans-serif;
}

.tip {
	font-size: 20px;
	margin: 40px auto 50px;
	text-align: center;
}

.cont {
	border-radius: 20px;
	overflow: hidden;
	position: relative;
	width: 900px;
	height: 650px;
	margin: 50px auto 100px;
	background: #fff;
	box-shadow: -10px -10px 15px rgba(255, 255, 255, 0.3), 10px 10px 15px
		rgba(70, 70, 70, 0.15), inset -10px -10px 15px
		rgba(255, 255, 255, 0.3), inset 10px 10px 15px rgba(70, 70, 70, 0.15);
}

.form {
	position: relative;
	width: 640px;
	height: 100%;
	-webkit-transition: -webkit-transform 1.2s ease-in-out;
	transition: -webkit-transform 1.2s ease-in-out;
	transition: transform 1.2s ease-in-out;
	transition: transform 1.2s ease-in-out, -webkit-transform 1.2s
		ease-in-out;
	padding: 50px 30px 0;
}

.sub-cont {
	overflow: hidden;
	position: absolute;
	left: 640px;
	top: 0;
	width: 900px;
	height: 100%;
	padding-left: 260px;
	background: #fff;
	-webkit-transition: -webkit-transform 1.2s ease-in-out;
	transition: -webkit-transform 1.2s ease-in-out;
	transition: transform 1.2s ease-in-out;
	transition: transform 1.2s ease-in-out, -webkit-transform 1.2s
		ease-in-out;
}

.cont.s--signup .sub-cont {
	-webkit-transform: translate3d(-640px, 0, 0);
	transform: translate3d(-640px, 0, 0);
}

button {
	display: block;
	margin: 0 auto;
	width: 260px;
	height: 36px;
	border-radius: 30px;
	color: #fff;
	font-size: 15px;
	cursor: pointer;
}

.img {
	overflow: hidden;
	z-index: 2;
	position: absolute;
	left: 0;
	top: 0;
	width: 260px;
	height: 100%;
	padding-top: 360px;
}

.img:before {
	content: '';
	position: absolute;
	right: 0;
	top: 0;
	width: 900px;
	height: 100%;
	background-image: url("ext.jpg");
	opacity: .8;
	background-size: cover;
	-webkit-transition: -webkit-transform 1.2s ease-in-out;
	transition: transform 1.2s ease-in-out;
	transition: transform 1.2s ease-in-out, -webkit-transform 1.2s
		ease-in-out;
}

.img:after {
	content: '';
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background: #3a4468;
}

.cont.s--signup .img:before {
	-webkit-transform: translate3d(640px, 0, 0);
	transform: translate3d(640px, 0, 0);
}

.img__text {
	z-index: 2;
	position: absolute;
	left: 0;
	top: 50px;
	width: 100%;
	padding: 0 20px;
	text-align: center;
	color: #fff;
	-webkit-transition: -webkit-transform 1.2s ease-in-out;
	transition: -webkit-transform 1.2s ease-in-out;
	transition: transform 1.2s ease-in-out;
	transition: transform 1.2s ease-in-out, -webkit-transform 1.2s
		ease-in-out;
}

.img__text h2 {
	margin-bottom: 10px;
	font-weight: normal;
}

.img__text p {
	font-size: 14px;
	line-height: 1.5;
}

.cont.s--signup .img__text.m--up {
	-webkit-transform: translateX(520px);
	transform: translateX(520px);
}

.img__text.m--in {
	-webkit-transform: translateX(-520px);
	transform: translateX(-520px);
}

.cont.s--signup .img__text.m--in {
	-webkit-transform: translateX(0);
	transform: translateX(0);
}

.img__btn {
	overflow: hidden;
	z-index: 2;
	position: relative;
	width: 100px;
	height: 36px;
	margin: 0 auto;
	background: transparent;
	color: #fff;
	text-transform: uppercase;
	font-size: 15px;
	cursor: pointer;
}

.img__btn:after {
	content: '';
	z-index: 2;
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	border: 2px solid #fff;
	border-radius: 30px;
}

.img__btn span {
	position: absolute;
	left: 0;
	top: 0;
	display: -webkit-box;
	display: flex;
	-webkit-box-pack: center;
	justify-content: center;
	-webkit-box-align: center;
	align-items: center;
	width: 100%;
	height: 100%;
	-webkit-transition: -webkit-transform 1.2s;
	transition: -webkit-transform 1.2s;
	transition: transform 1.2s;
	transition: transform 1.2s, -webkit-transform 1.2s;
}

.img__btn span.m--in {
	-webkit-transform: translateY(-72px);
	transform: translateY(-72px);
}

.cont.s--signup .img__btn span.m--in {
	-webkit-transform: translateY(0);
	transform: translateY(0);
}

.cont.s--signup .img__btn span.m--up {
	-webkit-transform: translateY(72px);
	transform: translateY(72px);
}

h2 {
	width: 100%;
	font-size: 26px;
	text-align: center;
}

label {
	display: block;
	width: 260px;
	margin: 25px auto 0;
	text-align: center;
}

label span {
	font-size: 12px;
	color: #cfcfcf;
	text-transform: uppercase;
}

input {
	display: block;
	width: 100%;
	margin-top: 5px;
	padding-bottom: 5px;
	font-size: 16px;
	border-bottom: 1px solid rgba(0, 0, 0, 0.4);
	text-align: center;
}

.forgot-pass {
	margin-top: 15px;
	text-align: center;
	font-size: 12px;
	color: #cfcfcf;
}

.submit {
	margin-top: 40px;
	margin-bottom: 20px;
	background: #3a4468;
	text-transform: uppercase;
}

.fb-btn {
	border: 2px solid #d3dae9;
	color: #8fa1c7;
}

.fb-btn span {
	font-weight: bold;
	color: #455a81;
}

.sign-in {
	-webkit-transition-timing-function: ease-out;
	transition-timing-function: ease-out;
}

.cont.s--signup .sign-in {
	-webkit-transition-timing-function: ease-in-out;
	transition-timing-function: ease-in-out;
	-webkit-transition-duration: 1.2s;
	transition-duration: 1.2s;
	-webkit-transform: translate3d(640px, 0, 0);
	transform: translate3d(640px, 0, 0);
}

.sign-up {
	-webkit-transform: translate3d(-900px, 0, 0);
	transform: translate3d(-900px, 0, 0);
}

.cont.s--signup .sign-up {
	-webkit-transform: translate3d(0, 0, 0);
	transform: translate3d(0, 0, 0);
}

.alert {
	padding: 20px;
	background-color: #f44336;
	color: white;
}

.closebtn {
	margin-left: 15px;
	color: white;
	font-weight: bold;
	float: right;
	font-size: 22px;
	line-height: 20px;
	cursor: pointer;
	transition: 0.3s;
}

.closebtn:hover {
	color: black;
}

a {
	color: unset
}

.disabled {
	opacity: 0.5;
	pointer-events: none;
}
</style>

<script>
	function disableSubmitButton(idButton) {
		var submitButton = document.getElementById(idButton);
		submitButton.disabled = true;
		clickedButton.classList.add("disabled");
		return true;
	}
</script>
</head>
<body>

	<br>
	<br>
	<center>
		<h1>VenusShop</h1>
	</center>
	<div class="cont">

		<div class="form sign-in">
			<c:if test="${param.message != null  }">
				<div class="alert">
					<span class="closebtn"
						onclick="this.parentElement.style.display='none';">&times;</span>
					<strong>Oops!</strong> Your email is already in use.
				</div>
			</c:if>
			<h2>Welcome</h2>
			<form method="post">
				<label> <span>Email</span> <input type="email" name="email" />
				</label> <label> <span>Password</span> <input type="password"
					name="password" />
				</label>
				<center>
					<span style="color: red;">${LoginMessage }</span>
				</center>
				<label> <a href="/forgot-password"><p>Forgot
							password?</p></a>
				</label>
				<button 
					type="submit" class="submit">Sign In</button>
			</form>

		</div>
		<div class="sub-cont">
			<div class="img">
				<div class="img__text m--up">

					<h3>
						Don't have an account? Please Sign up!
						<h3>
				</div>
				<div class="img__text m--in">

					<h3>
						If you already has an account, just sign in.
						<h3>
				</div>
				<div class="img__btn">
					<span class="m--up">Sign Up</span> <span class="m--in">Sign
						In</span>
				</div>
			</div>
			<div class="form sign-up">
				<h2>Create your Account</h2>
				<form action="/register" method="post"
					onsubmit="return validateForm()">
					<label> <span>Your full name</span> <input type="text"
						name="fullName" id="fullName" required />
					</label> <label> <span>Email</span> <input type="email"
						name="email" id="email" required />
					</label> <label> <span>Password</span> <input type="password"
						name="password" id="password" required />
					</label> <label> <span>Confirm your password</span> <input
						type="password" name="confirm-password" id="confirmPassword"
						required />
					</label> <label> <span>Your phone number</span> <input type="text"
						name="phoneNumber" required />
					</label> <label> <span>Your address</span> <input type="text"
						name="address" required />
					</label>
					<button onsubmit="disableSubmitButton('btnlogin')" id="btnlogin"
						type="submit" class="submit">Sign Up</button>
				</form>

			</div>
		</div>
	</div>

	<script>
		document.querySelector('.img__btn').addEventListener(
				'click',
				function() {
					document.querySelector('.cont').classList
							.toggle('s--signup');
				});

		function validateForm() {
			// Lấy giá trị của các trường dữ liệu
			var fullName = document.getElementById("fullName").value;
			var email = document.getElementById("email").value;
			var password = document.getElementById("password").value;
			var confirmPassword = document.getElementById("confirmPassword").value;

			// Kiểm tra các trường dữ liệu không được để trống
			if (fullName === "" || email === "" || password === ""
					|| confirmPassword === "") {
				alert("Vui lòng điền đầy đủ thông tin");
				return false;
			}

			// Kiểm tra định dạng email
			var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
			if (!emailRegex.test(email)) {
				alert("Email không hợp lệ");
				return false;
			}

			// Kiểm tra mật khẩu phải có ít nhất 6 ký tự
			if (password.length < 6) {
				alert("Mật khẩu phải có ít nhất 6 ký tự");
				return false;
			}

			// Kiểm tra xác nhận mật khẩu
			if (password !== confirmPassword) {
				alert("Xác nhận mật khẩu không khớp");
				return false;
			}

			return true;
		}
	</script>
</body>
</html>