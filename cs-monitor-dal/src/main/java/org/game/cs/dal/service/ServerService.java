package org.game.cs.dal.service;

import org.game.cs.common.domain.Server;
import org.game.cs.common.exception.ServerAlreadyRegisteredException;
import org.game.cs.dal.repository.ServerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServerService {

	@Autowired
	private ServerRepository serverRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void registerServer(String user, String ip, int port, String rcon) {
		if (serverRepository.findByUser(userService.findByName(user), ip) == null) {
			serverRepository.persist(new Server(ip, port, passwordEncoder
					.encodePassword(rcon, null), userService.findByName(user)));
		} else {
			throw new ServerAlreadyRegisteredException(
					"Server is already registered");
		}
	}
}
