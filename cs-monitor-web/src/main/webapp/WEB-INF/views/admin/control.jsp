<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<center>

	<form class="form-inline" method="post" action="<c:url value='/admin/executerconcommand' />">
		<input type="password" class="input-medium" name="rcon" placeholder="<spring:message code="text.rcon"/>" required>
        <input type="text" class="input-large" name="rcon_command" placeholder="<spring:message code="text.rcon.command"/>" required>
		<button type="submit" class="btn"><spring:message code="text.execute"/></button>
	</form>

	<div class="well">
		<c:forEach items="${info}" var="element">
            ${element.key}: ${element.value}<br />
		</c:forEach>
	</div>

</center>