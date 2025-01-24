package com.cts.smartspend.serviceImpl;

import com.cts.smartspend.dto.LoginDTO;
import com.cts.smartspend.dto.LoginResponseDTO;
import com.cts.smartspend.dto.UserDTO;
import com.cts.smartspend.entity.User;
import com.cts.smartspend.exception.UserNotFoundException;
import com.cts.smartspend.repo.UserRepo;
import com.cts.smartspend.security.JwtUtils;
import com.cts.smartspend.service.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        //user.setPassword(userDTO.getPassword());
        user.setRole(User.Role.valueOf(userDTO.getRole().toUpperCase()));
        User savedUser = userRepo.save(user);
        return convertToUserDTO(savedUser);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return users.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> getUserByRole(String role) {
        List<User> users = userRepo.findByRole(User.Role.valueOf(role.toUpperCase()));
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found with role: " + role);
        }
        return users.stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        //user.setPassword(userDTO.getPassword());
        user.setRole(User.Role.valueOf(userDTO.getRole().toUpperCase()));
        User savedUser = userRepo.save(user);
        return convertToUserDTO(savedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepo.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        userRepo.deleteById(id);
    }

    @Override
    public ResponseEntity<?> loginUser(LoginDTO loginDTO) {
        User user = userRepo.findByUsername(loginDTO.getUsername());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        System.out.println("password to be matched");
        boolean passwordMatches = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
        if (passwordMatches) {
            String token = jwtUtils.generateToken(user);
            return new ResponseEntity<>(new LoginResponseDTO(token, user.getRole().name()), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    private UserDTO convertToUserDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole().name());
    }
}
