$(document).ready(

function() {

	$("#bot_add_ct").selectBoxIt({
		showFirstOption : false
	});

	$("#bot_add_ct").bind({
		"change" : function() {
			bot_add("ct", $("#bot_add_ct").val());
		}
	});

	$("#bot_add_ct").data("selectBoxIt").dropdown.addClass("btn-primary");

	$("#bot_add_t").selectBoxIt({
		showFirstOption : false
	});

	$("#bot_add_t").bind({
		"change" : function() {
			bot_add("t", $("#bot_add_t").val());
		}
	});

	$("#bot_add_t").data("selectBoxIt").dropdown.addClass("btn-danger");

	function bot_add(team, difficulty) {
		$.get(location.href + "/bot/add/team=" + team + ";diff=" + difficulty);
	}

});

function player_kick(id) {
	$.get(location.href + "/kick/" + id);
}

function player_ban(id) {
	$.get(location.href + "/ban/" + id);
}