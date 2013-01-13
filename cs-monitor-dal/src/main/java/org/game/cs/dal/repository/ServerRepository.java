package org.game.cs.dal.repository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.game.cs.common.domain.Server;
import org.game.cs.common.domain.User;
import org.springframework.stereotype.Component;

@Component
public class ServerRepository extends AbstractGenericRepository<Server> {

	public ServerRepository() {
		super(Server.class);
	}

	public Server findByUser(User user, String address) {
		String queryString = "SELECT s FROM Server s WHERE s.address = '"
				+ address + "' AND s.user = '" + user.getId() + "'";
		Query query = entityManager.createQuery(queryString);
		Server result = null;
		try {
			result = (Server) query.getSingleResult();
		} catch (NoResultException exception) {
		}
		return result;
	}

}
