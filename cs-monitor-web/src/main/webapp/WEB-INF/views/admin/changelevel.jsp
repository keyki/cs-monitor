<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<center>

	<form class="form-inline" method="post"
		action="<c:url value='/admin/changelevel' />">
		<input type="text" class="span3" style="margin: 0 auto;"
			data-provide="typeahead" data-items="50" data-source="[${mapString}]"
			placeholder="<spring:message code="text.maps" />" name="map" />
		<button type="submit" class="btn">
			<spring:message code="text.change" />
		</button>
	</form>

	<div id="myCarousel" class="carousel slide" style="width:800px">
		<!-- Carousel items -->
		<div class="carousel-inner">
			<c:forEach items="${maps}" var="map" varStatus="counter">
				<div class="item ${counter.count == 0 ? 'active' : '' }">
					<img src="../resources/img/maps/${map}.jpg">
					<div class="carousel-caption">
						<form class="form-inline" method="post"
							action="<c:url value='/admin/changelevel' />">
							<h4>${map}</h4>
							<input type="hidden" name="map" value="${map}"/>
							<button type="submit" class="btn">
								<spring:message code="text.change" />
							</button>
						</form>
					</div>
				</div>
			</c:forEach>
		</div>
		<!-- Carousel nav -->
		<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
		<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
	</div>
</center>
