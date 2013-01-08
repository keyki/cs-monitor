package org.game.cs.dal.repository;

import org.game.cs.common.domain.Role;
import org.game.cs.common.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRepository extends AbstractGenericRepository<User> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserRepository() {
        super(User.class);
    }

    public void createTestUsers() {
        Role role_admin = new Role();
        role_admin.setName("ROLE_ADMIN");
        Role role_user = new Role();
        role_user.setName("ROLE_USER");

        User fsanyee = new User();
        fsanyee.setEnabled(true);
        fsanyee.setUsername("nemmondommeg");
        fsanyee.setPassword(passwordEncoder.encodePassword("fsanyee", null));
        fsanyee.addRole(role_user);
        fsanyee.addRole(role_admin);

        User keyki = new User();
        keyki.setEnabled(true);
        keyki.setUsername("nemmondommeg");
        keyki.setPassword(passwordEncoder.encodePassword("pina", null));
        keyki.addRole(role_user);
        keyki.addRole(role_admin);

        persist(keyki);
        persist(fsanyee);
    }

}
