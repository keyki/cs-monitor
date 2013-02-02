<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:url value="/resources/" var="resources" />
<c:url value="/" var="app" />


<security:authorize access="isAuthenticated()">
	<div id="connection-div"  >
		<form action="<c:url value='/admin/connect' />" method="post"
			class="form-horizontal connection" id="connectionform" style="width:100%">
			<div class="modal-header">
				<h3><spring:message code="text.connection.header"/></h3>
			</div>
			<div class="modal-body">
				<div class="control-group">
					<input type="text" placeholder="<spring:message code="text.ip.or.host"/>" name="ip"	required />
				</div>
				<div class="control-group">
					<input type="text" placeholder="<spring:message code="text.port"/>"		name="port" required value="27015" />
				</div>
				<div class="control-group">
					<input type="password" placeholder="<spring:message code="text.rcon"/>" name="rcon" />
				</div>
				<div class="control-group" id="modal-checkbox-id">
					<label class="checkbox"> 
						<input type="checkbox" name="register"> <spring:message code="text.save" />
					</label>
				</div>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn">
					<spring:message code="text.connect" />
				</button>
			</div>
		</form>
	</div>

</security:authorize>