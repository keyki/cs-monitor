package org.game.cs.web.service;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Service;

@Service
@RemoteProxy(name = "ChatService")
public class ChatService {

    @RemoteMethod
    public void sendMessage(String from, String message) {
    }

}
