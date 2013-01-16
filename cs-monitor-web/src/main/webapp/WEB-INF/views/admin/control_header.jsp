<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<security:authorize access="isAuthenticated()">
	<li class="dropdown"><a class="dropdown-toggle"
		data-toggle="dropdown" href="#"> <spring:message
				code="text.commands" /> <b class="caret"></b>
	</a>
		<ul class="dropdown-menu">
			<li><a href='<c:url value='/admin/control' />'><spring:message
						code="text.info" /></a></li>
			<li class="divider"></li>
			<li><a href='<c:url value='/admin/changelevel' />'><spring:message
						code="text.changelevel" /></a></li>
			<li><a href='<c:url value='/admin/players' />'><spring:message
						code="text.players" /></a></li>
		</ul></li>
</security:authorize>