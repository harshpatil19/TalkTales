package com.talktales.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.talktales.Config.LoginRequest;
import com.talktales.DTO.UserDTO;
import com.talktales.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "Operations related to users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto) {
        UserDTO createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @PutMapping("/{userid}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto, @PathVariable("userid") int uid) {
        UserDTO updatedUser = this.userService.updateUser(userDto, uid);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable int userid) {
        userService.deleteUser(userid);
        return new ResponseEntity<>(Map.of("message", "User Deleted Successfully"), HttpStatus.OK);
    }

    // ✅ FIXED: Changed from @PostMapping to @GetMapping
    @GetMapping("/{userid}")
    @Operation(summary = "Get user by ID", description = "Provide an ID to look up a specific user")
    public ResponseEntity<UserDTO> getSingleUser(@PathVariable int userid) {
        return ResponseEntity.ok(this.userService.getUserById(userid));
    }

    // ✅ FIXED: Login now returns user details
    @PostMapping("/login")
    @Operation(summary = "Login a user", description = "Provide email and password to authenticate a user")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        UserDTO user = userService.getUserByEmail(loginRequest.getEmail());
        
        if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
            return new ResponseEntity<>(Map.of("message", "Invalid email or password"), HttpStatus.UNAUTHORIZED);
        }

        // ✅ Store user in session
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);

        return ResponseEntity.ok(Map.of("message", "Login successful", "user", user));
    }

    // ✅ FIXED: getProfile now returns logged-in user properly
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return new ResponseEntity<>(Map.of("message", "User not logged in"), HttpStatus.UNAUTHORIZED);
        }
        UserDTO user = (UserDTO) session.getAttribute("user");
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUser());
    }
}
