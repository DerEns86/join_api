package dev.ens.join_backend.repository;

import dev.ens.join_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);

    Boolean existsByEmail(String email);
    Boolean existsByUserName(String username);
}