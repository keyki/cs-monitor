package org.game.cs.core.model;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.barkerjr.gameserver.GameServer.Request;
import net.barkerjr.gameserver.GameServer.RequestTimeoutException;
import net.barkerjr.gameserver.valve.SourceServer;

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
        sourceServer.load(5000, Request.INFORMATION);
        serverMap.put(user, sourceServer);
        return sourceServer;
    }

    public Map<ServerInfo, String> getBasicInformation(String user) throws RequestTimeoutException, IOException, InterruptedException {
        SourceServer server = serverMap.get(user);
        Map<ServerInfo, String> map = new HashMap<>();
        map.put(ServerInfo.SERVER_NAME, server.getName());
        map.put(ServerInfo.CURRENT_MAP, server.getMap());
        return map;
    }

    public void removeServer(String user) {
        serverMap.remove(user);
    }

}
