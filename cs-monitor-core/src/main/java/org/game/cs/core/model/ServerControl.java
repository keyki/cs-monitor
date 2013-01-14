package org.game.cs.core.model;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.security.auth.login.FailedLoginException;

import net.barkerjr.gameserver.GameServer.Request;
import net.barkerjr.gameserver.GameServer.RequestTimeoutException;
import net.barkerjr.gameserver.Player;
import net.barkerjr.gameserver.valve.SourceServer;

import org.game.cs.core.model.enums.RconCommand;
import org.game.cs.core.model.enums.ServerInfo;
import org.springframework.stereotype.Component;

@Component
public class ServerControl {

    private Map<String, SourceServer> serverMap;

    public ServerControl() {
        serverMap = new ConcurrentHashMap<>();
    }

    public SourceServer connect(String user, InetSocketAddress address) throws RequestTimeoutException, IOException, InterruptedException {
        SourceServer sourceServer = new SourceServer(address);
        load(sourceServer, Request.INFORMATION);
        serverMap.put(user, sourceServer);
        return sourceServer;
    }

    public SourceServer connect(String user, InetSocketAddress address, String password) throws RequestTimeoutException, IOException,
        InterruptedException {
        SourceServer sourceServer = connect(user, address);
        sourceServer.setRconPassword(password);
        return sourceServer;
    }

    public Map<ServerInfo, String> getBasicInformation(String user) throws RequestTimeoutException, IOException, InterruptedException {
        SourceServer server = getServer(user);
        load(server, Request.INFORMATION);
        Map<ServerInfo, String> map = new HashMap<>();
        map.put(ServerInfo.SERVER_NAME, server.getName());
        map.put(ServerInfo.CURRENT_MAP, server.getMap());
        map.put(ServerInfo.BOT_COUNT, String.valueOf(server.getBotCount()));
        map.put(ServerInfo.DEDICATED, server.getDedicated().toString());
        map.put(ServerInfo.MAX_PLAYERS, String.valueOf(server.getMaximumPlayers()));
        map.put(ServerInfo.NUMBER_OF_PLAYERS, String.valueOf(server.getNumberOfPlayers()));
        map.put(ServerInfo.OS, server.getOperatingSystem().toString());
        map.put(ServerInfo.PASSWORD_REQUIRED, String.valueOf(server.isPasswordRequired()));
        map.put(ServerInfo.VAC_SECURE, String.valueOf(server.isVacSecure()));
        return map;
    }

    public void removeServer(String user) {
        serverMap.remove(user);
    }

    private void load(SourceServer sourceServer, Request... requests) throws IOException, InterruptedException, RequestTimeoutException {
        sourceServer.load(10000, requests);
    }

    public String executeCommand(String user, String command) throws FailedLoginException, SocketTimeoutException {
        return executeCommand(getServer(user), command);
    }

    public String executeCommand(SourceServer server, String command) throws FailedLoginException, SocketTimeoutException {
        return server.sendRcon(command);
    }

    public String getAvaliableMaps(String user) throws FailedLoginException, SocketTimeoutException {
        return executeCommand(getServer(user), RconCommand.MAP_LIST.getValue());
    }

    public void changeMap(String user, String map) throws FailedLoginException, SocketTimeoutException {
        executeCommand(getServer(user), RconCommand.CHANGE_MAP.getValue() + map);
    }

    public Collection<Player> getPlayers(String user) throws RequestTimeoutException, IOException, InterruptedException {
        SourceServer server = getServer(user);
        load(server, Request.INFORMATION, Request.PLAYERS);
        return server.getPlayers().getPlayers();
    }
    
    private SourceServer getServer(String user){
        return serverMap.get(user);
    }

}
