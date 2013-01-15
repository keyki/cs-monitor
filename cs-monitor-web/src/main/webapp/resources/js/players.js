function player_kick(id){
	$.get(location.href + "/kick/" + id );
}

function player_ban(id){
	$.get(location.href + "/ban/" + id );
}