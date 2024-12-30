package com.cts.smartspend.service;

import com.cts.smartspend.dto.UserDTO;
import java.util.List;

public interface IUserService {
        UserDTO createUser(UserDTO userDTO);
        List<UserDTO> getAllUsers();
        List<UserDTO> getUserByRole(String role);
        UserDTO updateUser(Long id, UserDTO userDTO);
        void deleteUser(Long id);
}
