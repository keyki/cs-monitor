package org.game.cs.core.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.util.Map;

import javax.security.auth.login.FailedLoginException;

import net.barkerjr.gameserver.GameServer.RequestTimeoutException;
import net.barkerjr.gameserver.valve.SourceServer;

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

    public SourceServer connect(String user, String ip, int port) throws RequestTimeoutException, IOException, InterruptedException {
        userControl.addStatus(user, UserState.CONNECTED);
        return serverControl.connect(user, new InetSocketAddress(ip, port));
    }

    public Map<ServerInfo, String> getBasicInformation(String user) throws RequestTimeoutException, IOException, InterruptedException {
        return serverControl.getBasicInformation(user);
    }

    public void removeServer(String user) {
        serverControl.removeServer(user);
        userControl.removeUser(user);
    }

    public UserState getUserState(String user) {
        return userControl.getUserState(user);
    }

    public String executeCommand(String user, String password, String command) throws FailedLoginException, SocketTimeoutException {
        return serverControl.executeCommand(user, password, command);
    }

    public void expireConnection(String user) {
        serverControl.removeServer(user);
        userControl.addStatus(user, UserState.IDLE);
    }

}
