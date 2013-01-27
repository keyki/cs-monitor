<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:url value="/resources/" var="resources" />
<c:url value="/" var="app" />
<style>
.modal-footer{
	-webkit-box-shadow: rgba(255, 255, 255, 0.1) 0 1px 0, rgba(0, 0, 0, 0.8) 0 1px 7px 0px inset !important;
	-o-box-shadow: rgba(255, 255, 255, 0.1) 0 1px 0, rgba(0, 0, 0, 0.8) 0 1px 7px 0px inset !important;
	box-shadow: rgba(255, 255, 255, 0.1) 0 1px 0, rgba(0, 0, 0, 0.8) 0 1px 7px 0px inset !important;
}

.modal-body{
	padding: 15px 15px 10px 15px !important;
}

.modal-footer{
	background-color: #373737 !important;
}

#modal-checkbox-id{
	margin-bottom: 0px !important;
}

.connection {
	-moz-box-shadow: rgba(255, 255, 255, 0.1) 0 1px 0, rgba(0, 0, 0, 0.8) 0
		1px 7px 0px inset;
	-webkit-box-shadow: rgba(255, 255, 255, 0.1) 0 1px 0, rgba(0, 0, 0, 0.8)
		0 1px 7px 0px inset;
	-o-box-shadow: rgba(255, 255, 255, 0.1) 0 1px 0, rgba(0, 0, 0, 0.8) 0
		1px 7px 0px inset;
	box-shadow: rgba(255, 255, 255, 0.1) 0 1px 0, rgba(0, 0, 0, 0.8) 0 1px
		7px 0px inset;
	background: rgba(32, 32, 32, 0.9) !important;
	background-color: rgba(0, 0, 0, 0.3);
	border: 1px solid rgba(0, 0, 0, 0.05);
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	color: #333333;
	border-radius: 6px 6px 6px 6px !important;
}
</style>
<security:authorize access="isAuthenticated()">
	<form action="<c:url value='/admin/connect' />" method="post"
		class="form-horizontal connection">
		<div class="modal-header">
			<h3>Connection</h3>
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
</security:authorize>