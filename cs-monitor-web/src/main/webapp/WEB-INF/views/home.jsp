<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:url value="/resources/" var="resources" />

<html>
<link href="${resources}css/jquery.selectBoxIt.css" rel="stylesheet">
<link href="${resources}css/docs.css" rel="stylesheet">
<link href="${resources}css/bootstrap.css" rel="stylesheet">
<link href="${resources}css/bootstrap-responsive.css" rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<style>
body {
	padding-top: 40px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
<head>
<title>Home</title>
</head>
<body>

	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<security:authorize access="isAuthenticated()">
							<c:set var="username">
								<security:authentication property="principal.username" />
							</c:set>
							<li><a href='#'><spring:message code="text.loggedin" />
									${username}</a></li>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"><spring:message code="text.connect" /><b
									class="caret"></b></a>
								<ul class="dropdown-menu">
									<form action="<c:url value='/admin/connect' />" method="post"
										style="text-align: center;">
										<input type="text"
											placeholder="<spring:message code="text.ip.or.host"/>"
											name="ip" required /> <input type="text"
											placeholder="<spring:message code="text.port"/>" name="port"
											required value="27015" /> <input type="password"
											placeholder="<spring:message code="text.rcon"/>" name="rcon" />
										<label class="checkbox"
											style="margin-left: 40%; margin-right: 40%"> <input
											type="checkbox" name="register"> <spring:message
												code="text.save" />
										</label>
										<button type="submit" class="btn">
											<spring:message code="text.connect" />
										</button>
									</form>
								</ul></li>
							<c:if test="${fn:length(servers) > 0}">
								<li class="dropdown"><a class="dropdown-toggle"
									data-toggle="dropdown" href="#"> <spring:message
											code="text.servers" /> <b class="caret"></b>
								</a>
									<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
										<c:forEach items="${servers}" var="server">
											<li class="dropdown-submenu"><a tabindex="-1" href="#">${server.address}</a>
												<ul class="dropdown-menu">
													<form action="<c:url value='/admin/connect'/>"
														method="post" style="margin-bottom: 0px">
														<li><a tabindex="-1" href="#"
															onclick="$(this).closest('form').submit(); return false;"><spring:message
																	code="text.connect" /></a></li> <input type="hidden" name="ip"
															value="${server.address}"> <input type="hidden"
															name="port" value="${server.port}"> <input
															type="hidden" name="rcon" value="${server.password}">
													</form>
													<form action="<c:url value='/admin/remove'/>" method="post"
														style="margin-bottom: 0px">
														<li><a tabindex="-1" href="#"
															onclick="$(this).closest('form').submit(); return false;"><spring:message
																	code="text.remove" /></a></li> <input type="hidden" name="ip"
															value="${server.address}">
													</form>
												</ul></li>
										</c:forEach>
									</ul></li>
							</c:if>
						</security:authorize>
						<tiles:insertAttribute name="header" />
						<security:authorize access="isAuthenticated()">
							<li style="padding-right: 50px"><a
								href='<c:url value="/j_spring_security_logout"/>'><spring:message
										code="text.logout" /></a></li>
						</security:authorize>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="jumbotron masthead" id="cs_banner"
		style="background-image: url('${resources}img/banner.jpg');margin-bottom:20px;background-size:cover">
		<div class="container">
			<h2>CS-MONITOR</h2>
		</div>
	</div>

	<div class="container">
		<tiles:insertAttribute name="content" />
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
<script src="${resources}js/jquery.selectboxit.js"></script>
<script src="${resources}js/hover.js"></script>
<script src="${resources}js/parallax.js"></script>
<script src="${resources}js/home.js"></script>
</html>
