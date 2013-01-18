<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="center">

	<form class="form-inline" method="post" action="<c:url value='/admin/executerconcommand' />">
        <input type="text" class="input-large" name="rcon_command" placeholder="<spring:message code="text.rcon.command"/>" required>
		<button type="submit" class="btn"><spring:message code="text.execute"/></button>
	</form>

	<div style="text-align:center">
		<c:forEach items="${info}" var="element">
            ${element.key}: ${element.value}<br />
		</c:forEach>
	</div>

</div>