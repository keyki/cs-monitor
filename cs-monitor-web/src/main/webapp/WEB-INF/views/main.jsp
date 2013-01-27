<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
.well{
	-moz-box-shadow: rgba(255, 255, 255, 0.1) 0 1px 0, rgba(0, 0, 0, 0.8) 0 1px 7px 0px inset !important;
	-webkit-box-shadow: rgba(255, 255, 255, 0.1) 0 1px 0, rgba(0, 0, 0, 0.8) 0 1px 7px 0px inset !important;
	-o-box-shadow: rgba(255, 255, 255, 0.1) 0 1px 0, rgba(0, 0, 0, 0.8) 0 1px 7px 0px inset !important;
	box-shadow: rgba(255, 255, 255, 0.1) 0 1px 0, rgba(0, 0, 0, 0.8) 0 1px 7px 0px inset !important;
	background: #202020 !important;
	background-color: rgba(0, 0, 0, 0.3) !important;
	border: 1px solid rgba(0, 0, 0, 0.05) !important;
	-webkit-border-radius: 4px !important;
	background: rgba(32, 32, 32, 0.9) !important;
}
</style>
<security:authorize access="isAuthenticated()">
	<c:if test="${fn:length(servers) > 0}">
		<div class="well">
			<table class="table" style="margin-bottom: 0px;">
            	<thead>
              		<tr>
                 		<th>Name</th>
                  		<th>Connect</th>
                  		<th>Remove</th>
                	</tr>
              	</thead>
              <tbody>
              	<c:forEach items="${servers}" var="server">
              		<tr>
              			<td>
              				${server.address}
              			</td>
              			<td>
	              			<form action="<c:url value='/admin/connect'/>" method="post" class="btn"  onclick="$(this).closest('form').submit(); return false;">
								<i class="icon-play"></i> 
								<spring:message code="text.connect" />
								<input type="hidden" name="ip" value="${server.address}">
								<input type="hidden" name="port" value="${server.port}">
								<input type="hidden" name="rcon" value="${server.password}">
							</form>
              			</td>
              			<td>
              				<form action="<c:url value='/admin/remove'/>" method="post"	class="btn" onclick="$(this).closest('form').submit(); return false;">
								<i class="icon-remove"></i> 
								<spring:message code="text.remove" />
								<input type="hidden" name="ip" value="${server.address}">
							</form>
              			</td>
              		
                 
              		</tr>
              	</c:forEach>
              </tbody>
            </table>
	
		</div>
	</c:if>
</security:authorize>