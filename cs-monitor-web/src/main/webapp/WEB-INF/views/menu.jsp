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
						<li><a href='#'><spring:message code="text.loggedin" />${username}
						</a></li>
<!-- 						<li class="dropdown"><a href="#" class="dropdown-toggle" -->
<%-- 							data-toggle="dropdown"><spring:message code="text.connect" /><b --%>
<!-- 								class="caret"></b></a> -->
<!-- 							<ul class="dropdown-menu transparent-background-to-connection"> -->
<!-- 								<li> -->
<%-- 									<form action="<c:url value='/admin/connect' />" method="post" --%>
<!-- 										style="margin: 10 10 10px;"> -->
<!-- 										<fieldset> -->
<!-- 											<div class="control-group"> -->
<!-- 												<div class="span5"> -->
<!-- 													<div class="span2"> -->
<!-- 														<input type="text" -->
<%-- 															placeholder="<spring:message code="text.ip.or.host"/>" --%>
<!-- 															name="ip" style="width: 100%" required /> -->
<!-- 													</div> -->
<!-- 													<div class="span2"> -->
<!-- 														<input type="text" style="width: 100%" -->
<%-- 															placeholder="<spring:message code="text.port"/>" --%>
<!-- 															name="port" required value="27015" /> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class="control-group"> -->
<!-- 												<div class="span5"> -->
<!-- 													<div class="span2"> -->
<!-- 														<input type="password" style="width: 100%" -->
<%-- 															placeholder="<spring:message code="text.rcon"/>" --%>
<!-- 															name="rcon" /> -->
<!-- 													</div> -->
<!-- 													<div class="span2"> -->
<!-- 														<label class="checkbox"> <input type="checkbox" -->
<%-- 															name="register"> <spring:message code="text.save" /> --%>
<!-- 														</label> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											<div class=""> -->
<!-- 												<div class="span5" style="margin-right: 20px !important;"> -->
<!-- 													<button type="submit" class="btn" style="width: 100%"> -->
<%-- 														<spring:message code="text.connect" /> --%>
<!-- 													</button> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 										</fieldset> -->
<!-- 									</form> -->
<!-- 								</li> -->
<!-- 							</ul> -->
<!-- 						</li> -->
						<c:if test="${fn:length(servers) > 0}">
							<li class="dropdown"><a class="dropdown-toggle"
								data-toggle="dropdown" href="#"> <spring:message
										code="text.servers" /> <b class="caret"></b>
							</a>
								<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
									<c:forEach items="${servers}" var="server">
										<li class="dropdown-submenu"><a tabindex="-1" href="#">${server.address}</a>
											<ul class="dropdown-menu">
												<li>
													<form action="<c:url value='/admin/connect'/>"
														method="post" style="margin-bottom: 0px;">

														<a tabindex="-1" class="menulinks" href="#"
															onclick="$(this).closest('form').submit(); return false;">
															<i class="icon-play"></i> <spring:message
																code="text.connect" />
														</a> <input type="hidden" name="ip" value="${server.address}">
														<input type="hidden" name="port" value="${server.port}">
														<input type="hidden" name="rcon"
															value="${server.password}">
													</form>
												</li>
												<li>
													<form action="<c:url value='/admin/remove'/>" method="post"
														style="margin-bottom: 0px">
														<a class="menulinks" tabindex="-1" href="#"
															onclick="$(this).closest('form').submit(); return false;">
															<i class="icon-remove"></i> <spring:message
																code="text.remove" />
														</a> <input type="hidden" name="ip" value="${server.address}">
													</form>
												</li>
											</ul></li>
									</c:forEach>
								</ul></li>
						</c:if >
						<tiles:insertAttribute name="header" />
						<li style="padding-right: 50px"><a
							href='<c:url value="/j_spring_security_logout"/>'><spring:message
									code="text.logout" /><i class="icon-remove"
								style="margin-left: 5px; margin-top: 3px;"></i></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</security:authorize>