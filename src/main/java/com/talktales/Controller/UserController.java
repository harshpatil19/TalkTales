package com.talktales.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.talktales.DTO.UserDTO;
import com.talktales.Service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "Operations related to users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto){
	 UserDTO createUserDto=this.userService.createUser(userDto);
	 return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}

	@PutMapping("/{userid}")
	public ResponseEntity<UserDTO> updateUser(@ Valid @RequestBody UserDTO userDto, @PathVariable("userid") int uid){
		UserDTO updatedUser=this.userService.updateUser(userDto, uid);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable int userid){
        userService.deleteUser(userid);
        return new ResponseEntity<>(Map.of("message", "User Deleted Successfully"), HttpStatus.OK);
    }

	@GetMapping("/{userid}")
	 @Operation(summary = "Get user by ID", description = "Provide an ID to look up a specific user")
	public ResponseEntity<UserDTO> getSingleUser(@Valid @PathVariable int userid ){
		return ResponseEntity.ok(this.userService.getUserById(userid));
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>>getAllUser(){
		return ResponseEntity.ok(this.userService.getAllUser());
	}
	
	
	
	
	
}
