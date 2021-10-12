package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.model.User;
import com.library.service.UserService;

@RestController
@RequestMapping("library-spring/api/v2")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return this.userService.getAllUsers();
	}

	@GetMapping("/users/{offset}/{offsetSize}")
	public List<User> getAllUsers1(@PathVariable int offset, @PathVariable int offsetSize) {
		return this.userService.getAllUser1(offset, offsetSize);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable long id) {
		return this.userService.getUserById(id);
	}

	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return this.userService.createUser(user);
	}
	
	@PostMapping("/users")
	public User bookPenalty(@RequestBody User user) {
		return this.userService.createUser(user);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable long id) {
		return this.userService.deleteUser(id);
	}
}
