<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<center>

	<div class="well">
		<c:forEach items="${info}" var="element">
            ${element.key}: ${element.value}<br />
		</c:forEach>
	</div>

</center>