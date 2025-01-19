package com.cts.smartspend.service;

import com.cts.smartspend.dto.LoginDTO;
import com.cts.smartspend.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
        UserDTO createUser(UserDTO userDTO);
        List<UserDTO> getAllUsers();
        List<UserDTO> getUserByRole(String role);
        UserDTO updateUser(Long id, UserDTO userDTO);
        void deleteUser(Long id);

    ResponseEntity<?> loginUser(LoginDTO loginDTO);
}
