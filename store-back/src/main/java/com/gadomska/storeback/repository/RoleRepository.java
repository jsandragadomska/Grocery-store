package com.gadomska.storeback.repository;

import com.gadomska.storeback.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
