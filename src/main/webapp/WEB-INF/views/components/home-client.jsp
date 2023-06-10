<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!-- client section -->

<section class="client_section layout_padding-bottom">
	<div class="container">
		<div class="heading_container heading_center">
			<h2>
				<s:message code="web.feedback.title" />
			</h2>
		</div>
	</div>
	<div class="client_container ">
		<div id="carouselExample2Controls" class="carousel slide"
			data-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<div class="container">
						<div class="box">
							<div class="detail-box">
								<p>
									<i class="fa fa-quote-left" aria-hidden="true"></i>
								</p>
								<p>
									<s:message code="web.feedback.talk" />
								</p>
							</div>
							<div class="client-id">
								<div class="img-box">
									<img src="/images/client.jpg" alt="" style="height: 240px">
								</div>
								<div class="name">
									<h5><s:message code="web.feedback.person-name" /></h5>
									<h6><s:message code="web.feedback.person-title" /></h6>
								</div>
							</div>
						</div>
					</div>
				</div>


			</div>
			<div class="carousel_btn-box">
				<a class="carousel-control-prev" href="#carouselExample2Controls"
					role="button" data-slide="prev"> <span> <i
						class="fa fa-angle-left" aria-hidden="true"></i>
				</span> <span class="sr-only">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExample2Controls"
					role="button" data-slide="next"> <span> <i
						class="fa fa-angle-right" aria-hidden="true"></i>
				</span> <span class="sr-only">Next</span>
				</a>
			</div>
		</div>
	</div>
</section>
<!-- end client section -->