package org.game.cs.dal.repository;

import org.game.cs.common.domain.Server;
import org.springframework.stereotype.Component;

@Component
public class ServerRepository extends AbstractGenericRepository<Server> {

	public ServerRepository() {
		super(Server.class);
	}

}
