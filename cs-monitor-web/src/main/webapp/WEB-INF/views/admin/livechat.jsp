<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<input type="hidden" id="serverAddressHolder"
	value="${serverAddress.address.hostAddress}">

<center>

	<h1>Live chat</h1>
	<hr />

	<div id="chat_box"></div>
	<div class="form-inline">
		<input type="text" id="chat_message">
		<button class="btn btn-inverse" id="chat_message_send">
			<spring:message code="text.send" />
		</button>
	</div>

</center>

<script src="../../resources/js/livechat.js"></script>