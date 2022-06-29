package com.menezesdaniel.cadastroClientes.model.repository;

import com.menezesdaniel.cadastroClientes.model.entity.UserSystem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserSystem, Integer> {

    Optional<UserSystem> findByUsername(String username);

    boolean existsByUsername(String username);
}
