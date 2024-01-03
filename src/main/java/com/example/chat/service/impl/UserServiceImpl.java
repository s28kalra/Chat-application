package com.example.chat.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.chat.model.User;
import com.example.chat.model.User.Status;
import com.example.chat.repository.UserRepository;
import com.example.chat.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public User addUser(User user) {
		user.setStatus(Status.ONLINE);
		return userRepository.save(user);
	}

	@Override
	public User disconnect(User u) {
		var user = userRepository.findById(u.getNickName()).orElse(null);
		if (user != null) {
			user.setStatus(Status.OFFLINE);
			return userRepository.save(user);
		}
		return null;
	}

	@Override
	public List<User> findCnnectedUsers() {
		return userRepository.findALlByStatus(Status.ONLINE);
	}

}
