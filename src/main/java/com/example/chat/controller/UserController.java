package com.example.chat.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.chat.model.User;
import com.example.chat.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@MessageMapping("/user.addUser")
    @SendTo("/user/public")
	public User addUser(@Payload User user) {
		return userService.addUser(user);
	}

	@MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
	public User disconnect(User user) {
		return userService.disconnect(user);
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> findConnectedUsers() {
		return ResponseEntity.ok(userService.findCnnectedUsers());
	}

}
