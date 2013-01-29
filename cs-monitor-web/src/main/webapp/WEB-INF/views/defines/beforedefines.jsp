<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:url value="/resources/" var="resources" />
<c:url value="/" var="app" />
<link href="${resources}css/jquery.selectBoxIt.css" rel="stylesheet">
<link href="${resources}css/docs.css" rel="stylesheet">
<link href="${resources}css/darkstrap.css" rel="stylesheet">
<link href="${resources}css/customs.css" rel="stylesheet">
<link href="${resources}css/bootstrap.css" rel="stylesheet">
<link href="${resources}css/bootstrap-responsive.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>