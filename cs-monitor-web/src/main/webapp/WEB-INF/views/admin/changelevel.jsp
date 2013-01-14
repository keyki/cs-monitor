<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<center>

	<form class="form-inline" method="post"
		action="<c:url value='/admin/changelevel' />">
		<input type="text" class="span3" style="margin: 0 auto;"
			data-provide="typeahead" data-items="50" data-source="[${maps}]"
			placeholder="<spring:message code="text.maps" />" name="map" />
		<button type="submit" class="btn">
			<spring:message code="text.change" />
		</button>
	</form>

	<div id="myCarousel" class="carousel slide">
		<!-- Carousel items -->
		<div class="carousel-inner">
			<div class="active item">
				<img src="../resources/img/de_dust2.jpg">
				<div class="carousel-caption">
					<h4>de_dust2</h4>
				</div>
			</div>
			<div class="item">ITEM</div>
		</div>
		<!-- Carousel nav -->
		<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
		<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
	</div>
</center>
