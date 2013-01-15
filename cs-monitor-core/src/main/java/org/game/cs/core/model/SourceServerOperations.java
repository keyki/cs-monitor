package org.game.cs.core.model;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;

import org.game.cs.core.condenser.steam.SteamPlayer;
import org.game.cs.core.condenser.steam.exceptions.SteamCondenserException;
import org.game.cs.core.condenser.steam.servers.SourceServer;
import org.game.cs.core.model.enums.RconCommand;
import org.game.cs.core.model.enums.ServerInfo;
import org.springframework.stereotype.Component;

@Component
public class SourceServerOperations {

    private Map<String, SourceServer> serverMap;

    public SourceServerOperations() {
        serverMap = new ConcurrentHashMap<>();
    }

    public SourceServer connect(String user, InetSocketAddress address) throws SteamCondenserException {
        SourceServer sourceServer = new SourceServer(address.getAddress(), address.getPort());
        serverMap.put(user, sourceServer);
        return sourceServer;
    }

    public SourceServer connect(String user, InetSocketAddress address, String password) throws SteamCondenserException, TimeoutException {
        SourceServer sourceServer = connect(user, address);
        sourceServer.rconAuth(password);
        return sourceServer;
    }

    public Map<ServerInfo, String> getBasicInformation(String user) throws SteamCondenserException, TimeoutException {
        SourceServer server = getServerByUser(user);
        update(server);
        Map<ServerInfo, String> map = new HashMap<>();
        HashMap<String, Object> serverInfo = server.getServerInfo();
        map.put(ServerInfo.SERVER_NAME, server.getHostNames().get(0));
        map.put(ServerInfo.CURRENT_MAP, String.valueOf(serverInfo.get("mapName")));
        map.put(ServerInfo.BOT_COUNT, String.valueOf(serverInfo.get("numberOfBots")));
        map.put(ServerInfo.DEDICATED, String.valueOf(serverInfo.get("dedicated")));
        map.put(ServerInfo.MAX_PLAYERS, String.valueOf(serverInfo.get("maxPlayers")));
        map.put(ServerInfo.NUMBER_OF_PLAYERS, String.valueOf(serverInfo.get("numberOfPlayers")));
        map.put(ServerInfo.OS, String.valueOf(serverInfo.get("operatingSystem")));
        map.put(ServerInfo.PASSWORD_REQUIRED, String.valueOf(serverInfo.get("passwordProtected")));
        map.put(ServerInfo.VAC_SECURE, String.valueOf(serverInfo.get("secure")));
        return map;
    }

    public void removeServer(String user) {
        serverMap.remove(user);
    }

    private void update(SourceServer server) throws SteamCondenserException, TimeoutException {
        server.updateServerInfo();
        server.updatePlayers(server.getRcon_password());
    }

    public String executeCommand(String user, String command) throws TimeoutException, SteamCondenserException {
        return executeCommand(getServerByUser(user), command);
    }

    public String executeCommand(SourceServer server, String command) throws TimeoutException, SteamCondenserException {
        return server.rconExec(command);
    }

    public String getAvaliableMaps(String user) throws TimeoutException, SteamCondenserException {
        return executeCommand(getServerByUser(user), RconCommand.MAP_LIST.getValue());
    }

    public void changeMap(String user, String map) throws TimeoutException, SteamCondenserException {
        executeCommand(getServerByUser(user), RconCommand.CHANGE_MAP.getValue() + map);
    }

    public Collection<SteamPlayer> getPlayers(String user) throws SteamCondenserException, TimeoutException {
        SourceServer server = getServerByUser(user);
        update(server);
        return server.getPlayers().values();
    }

    private SourceServer getServerByUser(String user) {
        return serverMap.get(user);
    }

    public void kickPlayer(String user, int id) throws TimeoutException, SteamCondenserException {
        executeCommand(getServerByUser(user), RconCommand.KICK.getValue() + id);
    }

    public void banPlayer(String user, int id) throws TimeoutException, SteamCondenserException {
        executeCommand(getServerByUser(user), RconCommand.BAN.getValue() + id);
    }

}
