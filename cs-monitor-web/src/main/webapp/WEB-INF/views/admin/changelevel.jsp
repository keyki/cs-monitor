<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

changelevel

<div class="well">
	<c:forEach items="${maps}" var="element">
            ${element}<br />
	</c:forEach>
</div>