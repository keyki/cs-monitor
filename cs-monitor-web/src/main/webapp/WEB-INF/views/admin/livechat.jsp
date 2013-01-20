<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<input type="hidden" id="serverAddressHolder"
	value="${serverAddress.address.hostAddress}">

<center>

	<legend><h2 style="padding-top: 0px !important; margin-top: 0px !important;">Live chat</h2></legend>
	<div id="chat_box"></div>
	<div class="form-inline" style="text-align: center;">
		<input type="text" id="chat_message">
		<button class="btn btn-inverse" id="chat_message_send">
			<spring:message code="text.send" />
		</button>
	</div>

</center>

<script src="../../resources/js/livechat.js"></script>