package com.gadomska.storeback.service.impl;

import com.gadomska.storeback.domain.User;
import com.gadomska.storeback.domain.security.UserRole;
import com.gadomska.storeback.repository.RoleRepository;
import com.gadomska.storeback.repository.UserRepository;
import com.gadomska.storeback.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public User createUser(User user, Set<UserRole> userRoleSet) {
        User localUser = userRepository.findByUsername(user.getUsername());
        if (localUser != null){
            LOG.info("User with username {} already exist. Nothing will be done.", user.getUsername());
        } else {
            for (UserRole ur : userRoleSet) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoleSet);

            localUser = userRepository.save(user);
        }
        return localUser;
    }
}
