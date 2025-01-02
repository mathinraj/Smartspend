package com.cts.smartspend.controller;

import com.cts.smartspend.dto.UserDTO;
import com.cts.smartspend.serviceImpl.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //@PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO user = userService.createUser(userDTO);
        logger.info("User created successfully");
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        logger.info("Users found");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get/{role}")
    public ResponseEntity<List<UserDTO>> getUserByRole(@PathVariable String role) {
        List<UserDTO> users = userService.getUserByRole(role);
        logger.info("Users found");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        UserDTO user = userService.updateUser(id, userDTO);
        logger.info("User updated successfully");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        logger.info("User deleted successfully");
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }
}
