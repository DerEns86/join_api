package dev.ens.join_backend.services;



import dev.ens.join_backend.dtos.UserDTO;
import dev.ens.join_backend.model.User;

import java.util.List;

public interface UserService {
    void updateUserRole(Long userId, String roleName);

    List<User> getAllUsers();

    UserDTO getUserById(Long id);

    User findByUsername(String username);
}