package com.forestsoftware.usershop.repository;

import com.forestsoftware.usershop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByEmail(String email);
    Boolean existsByPhone (String phone);
    Optional<User>findByEmail(String email);
    Optional<User>findById(Long id);
}
