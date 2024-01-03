package com.example.chat.service;

import java.util.List;

import com.example.chat.model.User;

public interface UserService {

	User addUser(User user);

	User disconnect(User user);

	List<User> findCnnectedUsers();
}
