<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
body {
	background-image: url('../resources/img/errorbackground.jpg') !important;
	background-size: cover;
	padding-top: 100px;
}
</style>

<c:if test="${not empty error}">
	<div class="alert alert-error">${error}</div>
</c:if>