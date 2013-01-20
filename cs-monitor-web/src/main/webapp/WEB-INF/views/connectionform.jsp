<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:url value="/resources/" var="resources" />
<c:url value="/" var="app" />
<style>
.connection {
	padding-left: 10px !important;
	-moz-box-shadow: rgba(255, 255, 255, 0.1) 0 1px 0, rgba(0, 0, 0, 0.8) 0
		1px 7px 0px inset;
	-webkit-box-shadow: rgba(255, 255, 255, 0.1) 0 1px 0, rgba(0, 0, 0, 0.8)
		0 1px 7px 0px inset;
	-o-box-shadow: rgba(255, 255, 255, 0.1) 0 1px 0, rgba(0, 0, 0, 0.8) 0
		1px 7px 0px inset;
	box-shadow: rgba(255, 255, 255, 0.1) 0 1px 0, rgba(0, 0, 0, 0.8) 0 1px
		7px 0px inset;
	background: rgba(32,32,32,0.8) !important;
	background-color: rgba(0, 0, 0, 0.3);
	border: 1px solid rgba(0, 0, 0, 0.05);
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	color: #333333;
	
}
</style>

<form action="<c:url value='/admin/connect' />" method="post"
	class="form-horizontal connection">
	<legend>Connection</legend>
	<div class="control-group">


		<input type="text"
			placeholder="<spring:message code="text.ip.or.host"/>" name="ip"
			required />
	</div>
	<div class="control-group">

		<input type="text" placeholder="<spring:message code="text.port"/>"
			name="port" required value="27015" />
	</div>

	<div class="control-group">

		<input type="password"
			placeholder="<spring:message code="text.rcon"/>" name="rcon" />
	</div>


	<div class="control-group">

		<label class="checkbox"> <input type="checkbox"
			name="register"> <spring:message code="text.save" />
		</label>

		<button type="submit" class="btn">
			<spring:message code="text.connect" />
		</button>

	</div>
</form>