<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty error}">
	<div class="alert alert-error">${error}</div>
</c:if>