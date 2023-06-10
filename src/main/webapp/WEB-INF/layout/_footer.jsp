<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!-- info section -->
<section class="info_section mt-3">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<div class="info_contact">
					<h5>
						<a href="" class="navbar-brand"> <span> <s:message
									code="web.footer.brand" />
						</span>
						</a>
					</h5>
					<p>
						<i class="fa fa-map-marker" aria-hidden="true"></i>
						<s:message code="web.footer.address" />
					</p>
					<p>
						<i class="fa fa-phone" aria-hidden="true"></i> 0352 461 759
					</p>
					<p>
						<i class="fa fa-envelope" aria-hidden="true"></i>
						chauvanhieu.dev@gmail.com
					</p>
				</div>
			</div>
			<div class="col-md-3">
				<div class="info_info">
					<h5>
						<s:message code="web.footer.infomation" />
					</h5>
					<p>
						<s:message code="web.footer.infomation.description" />
					</p>
				</div>
			</div>
			<div class="col-md-3">
				<div class="info_links">
					<h5>
						<s:message code="web.footer.links" />
					</h5>
					<ul>
						<li><a href="/"> <s:message code="web.header.home" />
						</a></li>
						<li><a href="/about"> <s:message code="web.header.about" />
						</a></li>
						<li><a href="/product"> <s:message
									code="web.header.products" />
						</a></li>
						<li><a href="/why-us"> <s:message
									code="web.header.why-us" />
						</a></li>
						<li><a href="/testimonial"> <s:message
									code="web.header.testimonial" />
						</a></li>
					</ul>
				</div>
			</div>
			<div class="col-md-3">
				<div class="info_form ">
					<h5>
						<s:message code="web.footer.email" />
					</h5>
					<form action="">
						<input type="email"
							placeholder="<s:message
									code="web.footer.email.placeholder" />">
						<button>
							<s:message code="web.footer.email.button" />
						</button>
					</form>
					<div class="social_box">
						<a href=""> <i class="fa fa-facebook" aria-hidden="true"></i>
						</a> <a href=""> <i class="fa fa-twitter" aria-hidden="true"></i>
						</a> <a href=""> <i class="fa fa-instagram" aria-hidden="true"></i>
						</a> <a href=""> <i class="fa fa-youtube" aria-hidden="true"></i>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<!-- end info_section -->
<!-- footer section -->
<footer class="footer_section">
	<div class="container">
		<p>
			&copy; <span id="displayYear"></span> All Rights Reserved By <a
				href="https://www.facebook.com/venus19032001">VenusDev</a>
		</p>
	</div>
</footer>
<!-- footer section -->

<!-- jQery -->
<script src="js/jquery-3.4.1.min.js"></script>
<!-- bootstrap js -->
<script src="js/bootstrap.js"></script>
<!-- custom js -->
<script src="js/custom.js"></script>
