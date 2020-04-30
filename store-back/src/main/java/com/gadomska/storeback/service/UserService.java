package com.gadomska.storeback.service;

import com.gadomska.storeback.domain.User;
import com.gadomska.storeback.domain.security.UserRole;

import java.util.Set;

public interface UserService {
User createUser(User user, Set<UserRole> userRoleSet);
}
