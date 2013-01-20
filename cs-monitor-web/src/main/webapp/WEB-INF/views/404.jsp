<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:url value="/resources/" var="resources" />

<style>
<!--
.first {
	z-index: 109;
	top: 0px;
	position: absolute;
}

.second {
	z-index: 109;
	top: 60%;
	position: absolute;
	right: 0px;
}

img {
	opacity: 0.4;
	filter: alpha(opacity =     40);
}

.error_text {
	position: absolute !important;
	top: 30px !important;
	right: 50px !important;
}

.gratz {
	font: 52px/1.964 'Century Gothic', 'Lucida Sans Unicode',
		'Lucida Grande', 'Lucida Sans', Verdana, Arial, Helvetica, sans-serif;
	letter-spacing: -1px;
	font-weight: bold !important;
}

.broke {
	font: 32px/1.964 'Century Gothic', 'Lucida Sans Unicode',
		'Lucida Grande', 'Lucida Sans', Verdana, Arial, Helvetica, sans-serif;
	letter-spacing: -1px;
	letter-spacing: -1px !important;
}
-->
</style>

<img src="${resources}img/shatter1.png" class="first">
<div class="error_text">
	<strong class="gratz"><b><spring:message
				code="404.page.error.grat" /></b></strong> <strong class="broke"> <spring:message
			code="404.page.error.broke" />
	</strong>
</div>
<img src="${resources}img/shatter2.gif" class="second">