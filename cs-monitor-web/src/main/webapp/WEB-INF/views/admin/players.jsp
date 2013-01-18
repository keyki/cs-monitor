<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<center>

	<table class="table">
		<thead>
			<tr>
				<th><spring:message code="text.bots" /></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><button class="btn btn-inverse" onclick="bot_kick_all()">
						<spring:message code="text.bot.kickall" />
					</button> <select id="bot_add_ct">
						<option value="ct">
							<spring:message code="text.bot.addct" />
						</option>
						<option value="0">
							<spring:message code="text.bot.difficulty.0" />
						</option>
						<option value="1">
							<spring:message code="text.bot.difficulty.1" />
						</option>
						<option value="2">
							<spring:message code="text.bot.difficulty.2" />
						</option>
						<option value="3">
							<spring:message code="text.bot.difficulty.3" />
						</option>
				</select> <select id="bot_add_t">
						<option value="t">
							<spring:message code="text.bot.addt" />
						</option>
						<option value="0">
							<spring:message code="text.bot.difficulty.0" />
						</option>
						<option value="1">
							<spring:message code="text.bot.difficulty.1" />
						</option>
						<option value="2">
							<spring:message code="text.bot.difficulty.2" />
						</option>
						<option value="3">
							<spring:message code="text.bot.difficulty.3" />
						</option>
				</select>
			</tr>
		</tbody>
	</table>

	<table class="table">
		<thead>
			<tr>
				<th><spring:message code="text.player.kills" /></th>
				<th><spring:message code="text.player.name" /></th>
				<th><spring:message code="text.player.userid" /></th>
				<th><spring:message code="text.player.steamid" /></th>
				<th><spring:message code="text.player.ping" /></th>
				<th><spring:message code="text.player.address" /></th>
				<th><spring:message code="text.action" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${players}" var="player">
				<tr>
					<td>${player.kills}</td>
					<td>${player.name}</td>
					<td>${player.userid}</td>
					<td>${player.steamid}</td>
					<td>${player.ping}</td>
					<td>${player.address}</td>
					<td>
						<div class="btn-group">
							<button class="btn" onclick="player_kick(${player.userid})">
								<spring:message code="text.rcon.kick" />
							</button>
							<button class="btn dropdown-toggle" data-toggle="dropdown"
								style="height: 30px;">
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<li><a href="#" onclick="player_ban(${player.userid})"><spring:message
											code="text.rcon.ban" /></a></li>
							</ul>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</center>

<script src="../resources/js/players.js"></script>