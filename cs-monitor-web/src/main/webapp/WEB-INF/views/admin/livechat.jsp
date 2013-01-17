live chat

<div id="test"></div>
<script>
	var url;
	if (location.host.indexOf(":") != -1) {
		url = location.host.substr(0, location.host.indexOf(":"));
	} else {
		url = location.host;
	}
	var socket = new WebSocket("ws://" + url + ":5555/websocket/");

	socket.onmessage = function(event) {
		$("#test").append(event.data).append("<br/>");
	}

	socket.onopen = function() {
		console.log("open");
	}

	socket.onclose = function() {
		console.log("closed");
	}
</script>