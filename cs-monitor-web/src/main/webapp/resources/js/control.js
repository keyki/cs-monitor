$(document).ready(

function () {

    $("#server_basic_info").live("click", function () {
        get_basic_info();
    });

    function get_basic_info() {
        $.ajax({
            type: 'POST',
            url: location.href.substring(0, location.href.lastIndexOf("/")) + "/getbasicinfo",
            success: function (data) {
                alert(data.SERVER_NAME + " " + data.CURRENT_MAP);
            }
        });
    }
});