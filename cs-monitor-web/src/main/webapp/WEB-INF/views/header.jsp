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
	<security:authorize access="hasRole('ROLE_ADMIN')">
		<li><a href='<c:url value="/admin/control"/>'>Control</a></li>
	</security:authorize>
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