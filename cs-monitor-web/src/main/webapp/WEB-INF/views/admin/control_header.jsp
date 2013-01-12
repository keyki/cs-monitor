<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<security:authorize access="isAuthenticated()">
	<c:set var="username">
		<security:authentication property="principal.username" />
	</c:set>
	<li><a href='#'><spring:message code="text.loggedin" />
			${username}</a></li>

	<li class="dropdown"><a href="#" class="dropdown-toggle"
		data-toggle="dropdown"><spring:message code="text.connect" /><b
			class="caret"></b></a>
		<ul class="dropdown-menu">
			<form action="<c:url value='/admin/connect' />" method="post"
				style="text-align: center;">
				<input type="text"
					placeholder="<spring:message code="text.ip.or.host"/>" name="ip"
					required /> <input type="text"
					placeholder="<spring:message code="text.port"/>" name="port"
					required value="27015" /> <input type="password"
					placeholder="<spring:message code="text.rcon"/>" name="rcon" />
				<button type="submit" class="btn">
					<spring:message code="text.connect" />
				</button>
			</form>
		</ul></li>
	<li class="dropdown"><a class="dropdown-toggle"
		data-toggle="dropdown" href="#"> <spring:message
				code="text.commands" /> <b class="caret"></b>
	</a>
		<ul class="dropdown-menu">
			<li><a href='<c:url value='/admin/control' />'><spring:message
						code="text.info" /></a></li>
			<li><a href='<c:url value='/admin/changelevel' />'><spring:message
						code="text.changelevel" /></a></li>
		</ul></li>
	<li style="padding-right: 50px"><a
		href='<c:url value="/j_spring_security_logout"/>'><spring:message
				code="text.logout" /></a></li>
</security:authorize>

<security:authorize access="isAnonymous()">
	<li class="dropdown"><a href="#" class="dropdown-toggle"
		data-toggle="dropdown"><spring:message code="text.login" /><b
			class="caret"></b></a>
		<ul class="dropdown-menu">
			<form action="<c:url value='/j_spring_security_check' />"
				method="post" style="text-align: center;">
				<input type="text"
					placeholder="<spring:message code="text.username"/>"
					name="j_username" required /> <input type="password"
					placeholder="<spring:message code="text.password"/>"
					name="j_password" required />
				<button type="submit" class="btn">
					<spring:message code="text.login" />
				</button>
			</form>
		</ul></li>
</security:authorize>