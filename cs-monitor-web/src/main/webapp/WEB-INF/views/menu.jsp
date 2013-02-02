<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:url value="/resources/" var="resources" />
<c:url value="/" var="app" />


<security:authorize access="isAuthenticated()">
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<button class="btn btn-navbar" type="button" data-toggle="collapse" data-target=".nav-collapse">
					 <span class="icon-bar"></span> 
					 <span class="icon-bar"></span> 
					 <span class="icon-bar"></span>
				</button>
				<a class="brand" href="<c:url value='/' />" style="border-left: 0px !important;border-right: 0px !important;"><i class="icon-home"></i></a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<c:set var="username">
							<security:authentication property="principal.username" />
						</c:set>
						<li class="drop down">
							<a href='#'  class="dropdown-toggle userlink" data-toggle="dropdown">
								<i class="icon-user"></i><spring:message code="text.loggedin" />${username}
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a	href='<c:url value="/j_spring_security_logout"/>'>
										<i class="icon-remove" ></i>
										<spring:message code="text.logout" />
									</a>
								</li>
							</ul>
						</li>
						<c:if test="${fn:length(servers) > 0}">
							<li class="dropdown">
								<a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
									<spring:message code="text.servers" /> 
									<b class="caret"></b>
								</a>
								<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
									<c:forEach items="${servers}" var="server">
										<li class="dropdown-submenu">
											<a tabindex="-1" href="#">${server.address}</a>
											<ul class="dropdown-menu">
												<li>
													<form action="<c:url value='/admin/connect'/>" method="post" style="margin-bottom: 0px;">
														<a tabindex="-1" class="menulinks" href="#" onclick="$(this).closest('form').submit(); return false;">
															<i class="icon-play"></i> 
															<spring:message code="text.connect" />
														</a> 
														<input type="hidden" name="ip" value="${server.address}">
														<input type="hidden" name="port" value="${server.port}">
														<input type="hidden" name="rcon" value="${server.password}">
													</form>
												</li>
												<li>
													<form action="<c:url value='/admin/remove'/>" method="post"	style="margin-bottom: 0px">
														<a class="menulinks" tabindex="-1" href="#" 	onclick="$(this).closest('form').submit(); return false;">
															<i class="icon-remove"></i> 
															<spring:message code="text.remove" />
														</a> 
														<input type="hidden" name="ip" value="${server.address}">
													</form>
												</li>
											</ul>
										</li>
									</c:forEach>
								</ul></li>
						</c:if >
						<tiles:insertAttribute name="header" />
					</ul>
				</div>
			</div>
		</div>
	</div>
</security:authorize>
