package org.game.cs.core.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;

import net.barkerjr.gameserver.GameServer.RequestTimeoutException;
import net.barkerjr.gameserver.valve.SourceServer;

import org.game.cs.core.model.ServerControl;
import org.game.cs.core.model.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControlService {

    @Autowired
    private ServerControl serverControl;

    public SourceServer connect(String ip, int port) throws RequestTimeoutException, IOException, InterruptedException {
        return serverControl.connect(new InetSocketAddress(ip, port));
    }

    public Map<ServerInfo, String> getBasicInformation() {
        return serverControl.getBasicInformation();
    }

}
