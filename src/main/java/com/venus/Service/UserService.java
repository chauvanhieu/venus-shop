package com.venus.Service;

import org.springframework.stereotype.Service;

import com.venus.entities.User;

@Service
public interface UserService {
	void add(User user);

	void update(User user);

	void remove(int id);

	User get(int id);
}
