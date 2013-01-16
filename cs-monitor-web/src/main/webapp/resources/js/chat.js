$(function () {
	dwr.engine.setActiveReverseAjax(true);
    $("#messageForm").submit(function () {
        DwrService.sendMessage($("#nick").val(), $("#messageInput").val());
        $("#messageInput").val("");
        return false;
    });
});

function showMessage(from, message, date, id) {
}

$(document).ready(function () {
});