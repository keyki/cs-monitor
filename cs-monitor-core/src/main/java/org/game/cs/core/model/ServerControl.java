package org.game.cs.core.model;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import net.barkerjr.gameserver.GameServer.Request;
import net.barkerjr.gameserver.GameServer.RequestTimeoutException;
import net.barkerjr.gameserver.valve.SourceServer;

import org.springframework.stereotype.Component;

@Component
public class ServerControl {

    private SourceServer sourceServer;

    public SourceServer connect(InetSocketAddress address) throws RequestTimeoutException, IOException, InterruptedException {
        sourceServer = new SourceServer(address);
        sourceServer.load(5000, Request.INFORMATION);
        return sourceServer;
    }

    public Map<ServerInfo, String> getBasicInformation() {
        Map<ServerInfo, String> map = new HashMap<>();
        map.put(ServerInfo.SERVER_NAME, sourceServer.getName());
        map.put(ServerInfo.CURRENT_MAP, sourceServer.getMap());
        return map;
    }

    public SourceServer getSourceServer() {
        return sourceServer;
    }

}
