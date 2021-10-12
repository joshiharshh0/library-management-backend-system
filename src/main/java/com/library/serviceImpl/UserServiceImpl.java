package com.library.serviceImpl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.exception.UserNotFoundException;
import com.library.model.User;
import com.library.repository.UserRepository;
import com.library.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}

	@Override
	public List<User> getAllUser1(int offset, int offsetSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(offset, offsetSize);
		Page<User> list = userRepository.findAll(pageable);
		return list.getContent();
	}

	@Override
	public ResponseEntity<User> getUserById(long id) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(id).orElseThrow(() -> {
			LOG.info("Exception Message.....");
			LOG.error("UserNotFoundException occurs....");
			return new UserNotFoundException(id);
		});
		return ResponseEntity.ok().body(user);
	}

	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return this.userRepository.save(user);
	}

	@Override
	public ResponseEntity<?> deleteUser(long id) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		User user = userRepository.findById(id).orElseThrow(() -> {
			LOG.info("Exception Message.....");
			LOG.error("UserNotFoundException occurs....");
			return new UserNotFoundException(id);
		});
		userRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
