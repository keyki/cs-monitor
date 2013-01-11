<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<center>

	<form class="form-inline" method="post"
		action="<c:url value='/admin/changelevel' />">
		<input type="text" class="span3" style="margin: 0 auto;"
			data-provide="typeahead" data-items="50" data-source="[${maps}]" placeholder="<spring:message code="text.maps" />" name="map"/>
		<button type="submit" class="btn">
			<spring:message code="text.change" />
		</button>
	</form>
</center>
