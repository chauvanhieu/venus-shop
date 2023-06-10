<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<header class="header_section">
	<div class="header_top">
		<div class="container-fluid">
			<div class="top_nav_container">
				<div class="contact_nav">
					<i class="fa fa-phone" aria-hidden="true"></i> <span> <s:message
							code="web.header.call" /> : 0352 461 759
					</span> &nbsp &nbsp &nbsp &nbsp<i class="fa fa-envelope"
						aria-hidden="true"></i> <span> <s:message
							code="web.header.email" /> : chauvanhieu.dev@gmail.com
					</span>

				</div>
				<form class="search_form" action="/product" method="get"> <input
					type="text" class="form-control" placeholder="Search here..."
					name="keyword">
				<button class="" type="submit">
					<i class="fa fa-search" aria-hidden="true"></i>
				</button>
				</form>

				<c:choose>
					<c:when test="${user != null }">
						<div class="user_option_box">
							<a href="/account" class="account-link"> <i
								class="fa fa-user" aria-hidden="true"></i> <span> <s:message
										code="web.header.my-account" />
							</span>
							</a> <a href="/shopping-cart" class="cart-link"> <i
								class="fa fa-shopping-cart" aria-hidden="true"></i> <span>
									<s:message code="web.header.cart" />(${countCartElement })
							</span>
							</a> </a> <a href="/logout" class="cart-link"> <i
								class="fa-solid fa-right-from-bracket" style="color: #e0c71f;"></i>
								<span> <s:message code="web.header.logout" />
							</span>
							</a>
						</div>
					</c:when>
					<c:otherwise>
						<div class="user_option_box">
							<a href="/store-login" class="account-link"> <i
								class="fa-solid fa-right-to-bracket" style="color: #d3c41d;"></i>
								<span> <s:message code="web.header.login" />
							</span>
						</div>
					</c:otherwise>
				</c:choose>


			</div>

		</div>
	</div>
	<div class="header_bottom">
		<div class="container-fluid">
			<nav class="navbar navbar-expand-lg custom_nav-container ">
				<a class="navbar-brand" href="/"> <span> VenusShop </span>
				</a>

				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class=""> </span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav ">
						<li class="nav-item "><a class="nav-link" href="/"><s:message
									code="web.header.home" /> </a></li>
						<li class="nav-item"><a class="nav-link" href="/about"> <s:message
									code="web.header.about" /></a></li>
						<li class="nav-item"><a class="nav-link" href="/product"><s:message
									code="web.header.products" /></a></li>
						<li class="nav-item"><a class="nav-link" href="/why-us"><s:message
									code="web.header.why-us" /></a></li>
						<li class="nav-item"><a class="nav-link" href="/testimonial"><s:message
									code="web.header.testimonial" /></a></li>
						<li class="nav-item"><a class="nav-link" href="?lang=en">
								<img style="height: 20px" alt=""
								src="https://cleandye.com/wp-content/uploads/2020/01/English-icon.png">ENG
						</a></li>
						<li class="nav-item"><a class="nav-link" href="?lang=vi">
								<img style="height: 20px" alt=""
								src="https://cdn-icons-png.flaticon.com/512/323/323319.png">VIE
						</a></li>
					</ul>
				</div>
			</nav>
		</div>
	</div>
</header>