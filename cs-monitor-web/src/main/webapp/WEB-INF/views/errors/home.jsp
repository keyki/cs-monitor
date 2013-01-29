<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:url value="/resources/" var="resources" />
<c:url value="/" var="app" />
<html>
	<tiles:insertAttribute name="before" />
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
	<tiles:insertAttribute name="after" />
</html>
