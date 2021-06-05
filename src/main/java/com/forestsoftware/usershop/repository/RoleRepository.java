package com.forestsoftware.usershop.repository;

import com.forestsoftware.usershop.model.ERole;
import com.forestsoftware.usershop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole role);
}
