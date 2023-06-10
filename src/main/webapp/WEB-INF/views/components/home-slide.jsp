<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!-- slider section -->
<section class="slider_section ">
	<div id="customCarousel1" class="carousel slide" data-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active">
				<div class="container ">
					<div class="row">
						<div class="col-md-6">
							<div class="detail-box">
								<h1>
									<s:message code="web.slide.welcome.title" />
								</h1>
								<p>
									<s:message code="web.slide.welcome.description" />
								</p>
								<a href=""> <s:message code="web.slide.welcome.button" />
								</a>
							</div>
						</div>
						<div class="col-md-6">
							<div class="img-box">
								<img src="/images/slider-img.png" alt="">
							</div>
						</div>
					</div>
				</div>
			</div>


		</div>

	</div>
</section>
<!-- end slider section -->