package org.game.cs.core.model;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.security.auth.login.FailedLoginException;

import net.barkerjr.gameserver.GameServer.Request;
import net.barkerjr.gameserver.GameServer.RequestTimeoutException;
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
        loadInformation(sourceServer);
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
        SourceServer server = serverMap.get(user);
        loadInformation(server);
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

    private void loadInformation(SourceServer sourceServer) throws IOException, InterruptedException, RequestTimeoutException {
        sourceServer.load(10000, Request.INFORMATION);
    }

    public String executeCommand(String user, String command) throws FailedLoginException, SocketTimeoutException {
        SourceServer server = serverMap.get(user);
        return executeCommand(server, command);
    }

    public String executeCommand(SourceServer server, String command) throws FailedLoginException, SocketTimeoutException {
        return server.sendRcon(command);
    }

    public String getAvaliableMaps(String user) throws FailedLoginException, SocketTimeoutException {
        SourceServer server = serverMap.get(user);
        return executeCommand(server, RconCommand.MAP_LIST.getValue());
    }

    public void changeMap(String user, String map) throws FailedLoginException, SocketTimeoutException {
        SourceServer server = serverMap.get(user);
        executeCommand(server, RconCommand.CHANGE_MAP.getValue() + map);
    }

}
