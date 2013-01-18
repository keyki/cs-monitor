$(document).ready(

function () {

    $('#chat_box').slimScroll({
        position: 'left',
        height: '200px',
        width: '500px',
        start: 'bottom',
        railVisible: true,
        alwaysVisible: true
    });

    connect($("#serverAddressHolder").val());

    function connect(address) {

        var url;
        if (location.host.indexOf(":") != -1) {
            url = location.host.substr(0, location.host.indexOf(":"));
        } else {
            url = location.host;
        }

        var socket = new WebSocket("ws://" + url + ":5555/websocket/" + address);

        socket.onmessage = function (event) {
            $("#chat_box").append(event.data).append("<br/>");
            $("#chat_box").slimScroll({
                scrollTo: '200px'
            });
            console.log(event.data);
        };

        socket.onopen = function () {
            console.log("websocket opened");
        };

        socket.onclose = function () {
            console.log("websocket closed");
        };

    }

});