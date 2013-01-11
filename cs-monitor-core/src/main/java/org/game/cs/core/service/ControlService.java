package org.game.cs.core.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
        SourceServer server = serverControl.connect(user, new InetSocketAddress(ip, port));
        userControl.addStatus(user, UserState.CONNECTED);
        return server;
    }

    public SourceServer connect(String user, String ip, int port, String password) throws RequestTimeoutException, IOException, InterruptedException {
        SourceServer server = serverControl.connect(user, new InetSocketAddress(ip, port), password);
        userControl.addStatus(user, UserState.CONNECTED);
        return server;
    }

    public void setUserState(String user, UserState state) {
        userControl.addStatus(user, state);
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

    public Collection<String> getAvailableMaps(String user) throws FailedLoginException, SocketTimeoutException {
        List<String> list = new ArrayList<>();
        for (String s : serverControl.getAvaliableMaps(user).split(" ")) {
            if (s.contains("_")) {
                list.add(s.substring(0, s.lastIndexOf('.')));
            }
        }
        return list;
    }

    public void changeMap(String user, String map) throws FailedLoginException, SocketTimeoutException {
        serverControl.changeMap(user, map);
    }

}
