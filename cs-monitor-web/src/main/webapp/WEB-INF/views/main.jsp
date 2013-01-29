<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style>

</style>
<security:authorize access="isAuthenticated()">
	<c:if test="${fn:length(servers) > 0}">
		<div class="well">
			<table class="table table-hover" id="connection-table" style="margin-bottom: 0px;">
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
<%-- 								<spring:message code="text.connect" /> --%>
								<input type="hidden" name="ip" value="${server.address}">
								<input type="hidden" name="port" value="${server.port}">
								<input type="hidden" name="rcon" value="${server.password}">
							</form>
              			</td>
              			<td>
              				<form action="<c:url value='/admin/remove'/>" method="post"	class="btn btn-danger" onclick="$(this).closest('form').submit(); return false;">
								<i class="icon-remove"></i> 
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