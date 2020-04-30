package com.gadomska.storeback;

import com.gadomska.storeback.config.SecurityUtility;
import com.gadomska.storeback.domain.User;
import com.gadomska.storeback.domain.security.Role;
import com.gadomska.storeback.domain.security.UserRole;
import com.gadomska.storeback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class StoreBackApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(StoreBackApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setUsername("j");
        user1.setPassword(SecurityUtility.bCryptPasswordEncoder().encode("p"));
        user1.setEmail("jdoe@gmail.com");
        Set<UserRole> userRoleSet = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleId(1);
        role1.setName("ROLE_USER");
        userRoleSet.add(new UserRole(user1, role1));

        userService.createUser(user1, userRoleSet);
        userRoleSet.clear();

        User user2 = new User();
        user2.setFirstName("Admin");
        user2.setLastName("Admin");
        user2.setUsername("admin");
        user2.setPassword(SecurityUtility.bCryptPasswordEncoder().encode("p"));
        user2.setEmail("admin@gmail.com");
        Role role2 = new Role();
        role2.setRoleId(0);
        role2.setName("ROLE_ADMIN");
        userRoleSet.add(new UserRole(user2, role2));
        userService.createUser(user2, userRoleSet);
    }
}
