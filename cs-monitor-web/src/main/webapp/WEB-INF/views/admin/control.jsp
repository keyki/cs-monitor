<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<center>

	<form class="form-inline" action="<c:url value="/admin/connect"/>"
		method="POST">
		<input type="text" class="input-medium" name="ip"
			placeholder="<spring:message code="text.ip.or.host"/>" required><input
			type="text" class="input-medium" name="port"
			placeholder="<spring:message code="text.port"/>" value="27015"
			required> <input type="password" class="input-medium"
			name="rcon" placeholder="<spring:message code="text.rcon"/>">
		<button type="submit" class="btn">
			<spring:message code="text.connect" />
		</button>
	</form>
	<button class="btn" id="server_basic_info">Basic Info</button>

</center>