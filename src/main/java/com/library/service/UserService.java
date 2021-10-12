package com.library.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.library.model.User;

public interface UserService {
	public List<User> getAllUsers();

	public ResponseEntity<User> getUserById(long id);

	public User createUser(User user);

	public ResponseEntity<?> deleteUser(long id);

	public List<User> getAllUser1(int offset, int offsetSize);
}
