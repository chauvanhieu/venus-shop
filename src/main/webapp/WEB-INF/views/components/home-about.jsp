<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!-- about section -->

<section class="about_section">
	<div class="container-fluid  ">
		<div class="row">
			<div class="col-md-5 ml-auto">
				<div class="detail-box pr-md-3">
					<div class="heading_container">
						<h2>
							<s:message code="web.about.title" />
						</h2>
					</div>
					<p>
						<s:message code="web.about.description" />
					</p>
					<a href=""> <s:message code="web.about.button" /> </a>
				</div>
			</div>
			<div class="col-md-6 px-0">
				<div class="img-box">
					<img src="/images/about-img.jpg" alt="">
				</div>
			</div>
		</div>
	</div>
</section>

<!-- end about section -->