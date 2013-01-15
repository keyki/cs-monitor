<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<center>

	<table class="table table-bordered">
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
								<li><a href="#"><spring:message code="text.rcon.ban" /></a></li>
							</ul>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</center>

<script src="../resources/js/players.js"></script>