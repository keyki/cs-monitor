package org.game.cs.dal.service;

import org.game.cs.dal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createMasterUsers() {
        userRepository.createTestUsers();
    }

}
