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
import org.game.cs.core.model.ServerControl;
import org.game.cs.core.model.UserControl;
import org.game.cs.core.model.enums.ServerInfo;
import org.game.cs.core.model.enums.UserState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControlService {

    @Autowired
    private ServerControl serverControl;
    @Autowired
    private UserControl userControl;

    public SourceServer connect(String user, String ip, int port) throws SteamCondenserException {
        SourceServer server = serverControl.connect(user, new InetSocketAddress(ip, port));
        userControl.addStatus(user, UserState.CONNECTED);
        return server;
    }

    public SourceServer connect(String user, String ip, int port, String password) throws SteamCondenserException, TimeoutException {
        SourceServer server = serverControl.connect(user, new InetSocketAddress(ip, port), password);
        userControl.addStatus(user, UserState.CONNECTED);
        return server;
    }

    public void setUserState(String user, UserState state) {
        userControl.addStatus(user, state);
    }

    public Map<ServerInfo, String> getBasicInformation(String user) throws SteamCondenserException, TimeoutException {
        return serverControl.getBasicInformation(user);
    }

    public void removeServer(String user) {
        serverControl.removeServer(user);
        userControl.removeUser(user);
    }

    public UserState getUserState(String user) {
        return userControl.getUserState(user);
    }

    public String executeCommand(String user, String command) throws TimeoutException, SteamCondenserException {
        return serverControl.executeCommand(user, command);
    }

    public void expireConnection(String user) {
        serverControl.removeServer(user);
        userControl.addStatus(user, UserState.IDLE);
    }

    public Collection<String> getAvailableMaps(String user) throws TimeoutException, SteamCondenserException {
        List<String> list = new ArrayList<>();
        for (String s : serverControl.getAvaliableMaps(user).split(" ")) {
            if (s.contains("_")) {
                list.add(s.substring(0, s.lastIndexOf('.')));
            }
        }
        return list;
    }

    public void changeMap(String user, String map) throws TimeoutException, SteamCondenserException {
        serverControl.changeMap(user, map);
    }

    public Collection<PlayerDto> getPlayers(String user) throws SteamCondenserException, TimeoutException {
        Collection<PlayerDto> players = new ArrayList<>();
        for (SteamPlayer player : serverControl.getPlayers(user)) {
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

    public void kickPlayer(String user, int id) throws TimeoutException, SteamCondenserException {
        serverControl.kickPlayer(user, id);
    }

    public void banPlayer(String user, int id) throws TimeoutException, SteamCondenserException {
        serverControl.banPlayer(user, id);
    }

}
