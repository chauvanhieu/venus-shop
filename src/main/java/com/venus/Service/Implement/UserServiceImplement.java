package com.venus.Service.Implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venus.Service.UserService;
import com.venus.entities.User;
import com.venus.repository.UserRepository;

@Service
public class UserServiceImplement implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public void add(User user) {
		userRepository.save(user);
	}

	@Override
	public void update(User user) {
		userRepository.save(user);
	}

	@Override
	public void remove(int id) {
		User user = get(id);
		user.setStatus(0);
		userRepository.save(user);
	}

	@Override
	public User get(int id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			return null;
		}
		return user.get();
	}

}
