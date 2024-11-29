package dev.ens.join_backend.repository;


import dev.ens.join_backend.model.AppRole;
import dev.ens.join_backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);

}