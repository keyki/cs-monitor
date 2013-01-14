package org.game.cs.web.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.game.cs.common.domain.Role;
import org.game.cs.common.domain.User;
import org.game.cs.dal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class MyUserDetailsService implements UserDetailsService, Serializable {

	@Autowired
	private UserService userService;
	private static final long serialVersionUID = -639528008813643968L;

	public UserDetails loadUserByUsername(String username) {
		User user = userService.findByName(username);
		Set<Role> roles = user.getRoles();
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		for (final Role ur : roles) {
			authorities.add(new GrantedAuthority() {
				private static final long serialVersionUID = -7455448721898193788L;

				@Override
				public String getAuthority() {
					return ur.getName();
				}
			});
		}
		org.springframework.security.core.userdetails.User authUser = new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), user.isEnabled(), true,
				true, true, authorities);
		return authUser;
	}
}
