package com.gustavo.apptarefas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.apptarefas.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByEmail(String email);
}
