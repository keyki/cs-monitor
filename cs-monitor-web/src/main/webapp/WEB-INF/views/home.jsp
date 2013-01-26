<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:url value="/resources/" var="resources" />
<c:url value="/" var="app" />

<html>
<link href="${resources}css/jquery.selectBoxIt.css" rel="stylesheet">
<link href="${resources}css/docs.css" rel="stylesheet">

<link href="${resources}css/darkstrap.css" rel="stylesheet">
<link href="${resources}css/customs.css" rel="stylesheet">
<link href="${resources}css/bootstrap.css" rel="stylesheet">
<link href="${resources}css/bootstrap-responsive.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>

<c:choose>
	<c:when test="${empty error}">
		<c:set value="url('${resources}img/background1.jpg') !important;"
			var="background" />
	</c:when>
	<c:otherwise>
		<c:set value="url('${resources}img/errorbackground.jpg') !important;"
			var="background" />
	</c:otherwise>
</c:choose>
<style>
body {
	background-image: ${background};
	background-size:cover !important;
	padding-top:100px !important;
	}
 
</style>

<head>
<link REL="SHORTCUT ICON" HREF="${app}favicon.ico" />
<title>Home</title>
</head>
<body data-spy="scroll" data-target=".bs-docs-sidebar" id="homebody" data-twttr-rendered="true">
	<tiles:insertAttribute name="menu" />
	<div class="container">
		<div class="row">
		<div class="span3 bs-docs-sidebar" style="margin-left: 10px !important;">
			<tiles:insertAttribute name="connectionform" />
		</div>
		<div class="span8 offset1">
			<tiles:insertAttribute name="content" />
		</div>
		</div>
	</div>

</body>
<script src="${resources}js/bootstrap-transition.js"></script>
<script src="${resources}js/bootstrap-alert.js"></script>
<script src="${resources}js/bootstrap-modal.js"></script>
<script src="${resources}js/bootstrap-dropdown.js"></script>
<script src="${resources}js/bootstrap-scrollspy.js"></script>
<script src="${resources}js/bootstrap-tab.js"></script>
<script src="${resources}js/bootstrap-tooltip.js"></script>
<script src="${resources}js/bootstrap-popover.js"></script>
<script src="${resources}js/bootstrap-button.js"></script>
<script src="${resources}js/bootstrap-collapse.js"></script>
<script src="${resources}js/bootstrap-carousel.js"></script>
<script src="${resources}js/bootstrap-typeahead.js"></script>
<script src="${resources}js/jquery-ui-1.9.2.custom.js"></script>
<script src="${resources}js/jquery.slimscroll.js"></script>
<script src="${resources}js/jquery.selectboxit.js"></script>
<script src="${resources}js/hover.js"></script>
<script src="${resources}js/parallax.js"></script>
<script src="${resources}js/home.js"></script>
</html>
