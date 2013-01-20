package org.game.cs.core.service;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.game.cs.common.domain.PlayerDto;
import org.game.cs.core.condenser.steam.SteamPlayer;
import org.game.cs.core.condenser.steam.exceptions.SteamCondenserException;
import org.game.cs.core.condenser.steam.servers.SourceServer;
import org.game.cs.core.model.SourceServerOperations;
import org.game.cs.core.model.UserControl;
import org.game.cs.core.model.enums.ServerInfo;
import org.game.cs.core.model.enums.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceServerService {

	@Autowired
	private SourceServerOperations sourceServerOperations;
	@Autowired
	private UserControl userControl;

	public SourceServer connect(String user, String ip, int port)
			throws SteamCondenserException {
		SourceServer server = sourceServerOperations.connect(user,
				new InetSocketAddress(ip, port));
		userControl.addStatus(user, UserState.CONNECTED);
		return server;
	}

	public SourceServer connect(String user, String ip, int port,
			String password) throws SteamCondenserException, TimeoutException {
		SourceServer server = sourceServerOperations.connect(user,
				new InetSocketAddress(ip, port), password);
		userControl.addStatus(user, UserState.CONNECTED);
		return server;
	}

	public void setUserState(String user, UserState state) {
		userControl.addStatus(user, state);
	}

	public Map<ServerInfo, String> getBasicInformation(String user)
			throws SteamCondenserException, TimeoutException {
		return sourceServerOperations.getBasicInformation(user);
	}

	public void removeServer(String user) {
		sourceServerOperations.removeServer(user);
		userControl.removeUser(user);
	}

	public UserState getUserState(String user) {
		return userControl.getUserState(user);
	}

	public String executeCommand(String user, String command)
			throws TimeoutException, SteamCondenserException {
		return sourceServerOperations.executeCommand(user, command);
	}

	public void expireConnection(String user) {
		sourceServerOperations.removeServer(user);
		userControl.addStatus(user, UserState.IDLE);
	}

	public Collection<String> getAvailableMaps(String user)
			throws TimeoutException, SteamCondenserException {
		List<String> list = new ArrayList<>();
		for (String s : sourceServerOperations.getAvaliableMaps(user)
				.split(" ")) {
			if (s.contains("_")) {
				list.add(s.substring(0, s.lastIndexOf('.')));
			}
		}
		return list;
	}

	public void changeMap(String user, String map) throws TimeoutException,
			SteamCondenserException {
		sourceServerOperations.changeMap(user, map);
	}

	public Collection<PlayerDto> getPlayers(String user)
			throws SteamCondenserException, TimeoutException {
		Collection<PlayerDto> players = new ArrayList<>();
		for (SteamPlayer player : sourceServerOperations.getPlayers(user)) {
			PlayerDto playerDto = new PlayerDto();
			playerDto.setKills(player.getScore());
			playerDto.setName(player.getName());
			playerDto.setUserid(player.getRealId());
			playerDto.setSteamid(player.getSteamId());
			playerDto.setPing(player.getPing());
			playerDto.setAddress(player.getIpAddress());
			players.add(playerDto);
		}
		return players;
	}


	public void kickPlayer(String user, int id) throws TimeoutException,
			SteamCondenserException {
		sourceServerOperations.kickPlayer(user, id);
	}

	public void banPlayer(String user, int id) throws TimeoutException,
			SteamCondenserException {
		sourceServerOperations.banPlayer(user, id);
	}

	public void addBot(String user, String team, int difficulty)
			throws TimeoutException, SteamCondenserException {
		sourceServerOperations.setBotDifficutly(user, difficulty);
		sourceServerOperations.addBot(user, team);
	}

	public void kickAllBot(String user) throws TimeoutException,
			SteamCondenserException {
		sourceServerOperations.kickAllBot(user);
	}

	public void addLogAddress(String user, String logAddress)
			throws TimeoutException, SteamCondenserException {
		sourceServerOperations.addLogAddress(user, logAddress);
	}

	public InetSocketAddress getServerAddress(String user) {
		return sourceServerOperations.getServerAddress(user);
	}

	public void sendChatMessage(String user, String message)
			throws TimeoutException, SteamCondenserException {
		sourceServerOperations.sendChatMessage(user, message);
	}

}
