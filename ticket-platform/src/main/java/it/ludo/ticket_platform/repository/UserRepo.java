package it.ludo.ticket_platform.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import it.ludo.ticket_platform.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}








