<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="center logindiv">

	<div class="tabbable" style="width: 150%">
		<ul class="nav nav-tabs">
			<li class="active"><a href="#tab1" data-toggle="tab"> <spring:message
						code="text.login" />
			</a></li>
			<li><a href="#tab2" data-toggle="tab"> <spring:message
						code="text.register" /></a></li>
		</ul>
		<div class="tab-content transparent">
			<div class="tab-pane active" id="tab1" style="width:50%;margin-right:25%">
				<form class="form-horizontal" action="<c:url value='/j_spring_security_check' />" method="post">
					<div class="control-group">
						<label class="control-label" for="j_username"><spring:message code="text.username"/></label>
						<div class="controls">
							<input type="text" name="j_username" required>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="j_password"><spring:message code="text.password"/></label>
						<div class="controls">
							<input type="password" name="j_password" required>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<label class="checkbox"> <input type="checkbox" name="_spring_security_remember_me">
								<spring:message code="text.rememberme" />
							</label>
							<button type="submit" class="btn"><spring:message code="text.login"/></button>
						</div>
					</div>
				</form>
			</div>
			<div class="tab-pane" id="tab2">
				<p>Currently not available.</p>
			</div>
		</div>
	</div>

</div>