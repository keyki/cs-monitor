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
			<li>
				<a href='<c:url value='/admin/control' />'>
					<i class="icon-info-sign"> </i>
					<spring:message	code="text.info" />
				</a>
			</li>
			<li class="divider"></li>
			<li>
				<a href='<c:url value='/admin/changelevel' />'>
					<i class="icon-map-marker"> </i>
					<spring:message	code="text.changelevel" />
				</a>
			</li>
			<li>
				<a href='<c:url value='/admin/players' />'>
					<i class="icon-user"> </i>
					<spring:message	code="text.players" />
				</a>
			</li>
			<li>
				
				<a href='<c:url value='/admin/live/chat' />'>
					<i class="icon-comment"> </i>
					<spring:message	code="text.live.chat" />
				</a>
			</li>
		</ul></li>
</security:authorize>