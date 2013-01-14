package org.game.cs.dal.service;

import java.util.Collection;

import org.game.cs.common.domain.Server;
import org.game.cs.common.domain.User;
import org.game.cs.common.exception.ServerAlreadyRegisteredException;
import org.game.cs.dal.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServerService {

    @Autowired
    private ServerRepository serverRepository;
    @Autowired
    private UserService userService;

    public void registerServer(String user, String ip, int port, String rcon) {
        if (!isServerRegistered(user, ip)) {
            saveServer(user, ip, port, rcon);
        } else {
            throw new ServerAlreadyRegisteredException("Server is already registered");
        }
    }

    public Collection<Server> findAllByUser(String user) {
        return serverRepository.findAllByUser(findUser(user));
    }

    private boolean isServerRegistered(String user, String ip) {
        return findByUser(user, ip) != null;
    }

    private Server findByUser(String user, String ip) {
        return serverRepository.findByUser(findUser(user), ip);
    }

    private User findUser(String user) {
        return userService.findByName(user);
    }

    private void saveServer(String user, String ip, int port, String rcon) {
        serverRepository.persist(new Server(ip, port, rcon, findUser(user)));
    }

    public void remove(String user, String address) {
        serverRepository.remove(findByUser(user, address));
    }
}
