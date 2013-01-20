<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:url value="/resources/" var="resources" />
<c:url value="/" var="app" />
<html>
<link href="${resources}css/jquery.selectBoxIt.css" rel="stylesheet">
<%-- <link href="${resources}css/docs.css" rel="stylesheet"> --%>



<link href="${resources}css/darkstrap.css" rel="stylesheet">
<link href="${resources}css/customs.css" rel="stylesheet">
<link href="${resources}css/bootstrap.css" rel="stylesheet">
<link href="${resources}css/bootstrap-responsive.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<style>
body {
	height:100%;
	width: 100%;
	position:absolute; 
	top:0; 
	left:0; 
	right:0; 
	bottom:0; 
	 overflow: hidden;
	background-image: url('${resources}img/error.jpg')  !important;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-repeat:no-repeat;
	background-position:center;
	background-size: cover;
}


</style>
<head>
<link rel="SHORTCUT ICON" href="${app}favicon.ico" />
<title>404</title>
</head>
<body>
	<tiles:insertAttribute name="content" />
	
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
