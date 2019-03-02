package com.railroad.dao;

import com.railroad.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
    Role getRoleById(Long id);
}
