<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<form class="form-inline" action="<c:url value="/admin/connect"/>"
	method="POST">
	<input type="text" class="input-medium" name="ip"
		placeholder="<spring:message code="text.ip.or.host"/>"><input
		type="text" class="input" name="port"
		placeholder="<spring:message code="text.port"/>"> <input
		type="password" class="input" name="rcon"
		placeholder="<spring:message code="text.rcon"/>">
	<button type="submit" class="btn">
		<spring:message code="text.connect" />
	</button>

	<c:if test="${reponse}">
		<c:out value="${response}" />
	</c:if>
</form>