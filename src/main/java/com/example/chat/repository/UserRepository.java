package com.example.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.chat.model.User;
import com.example.chat.model.User.Status;

public interface UserRepository extends JpaRepository<User, String> {

	List<User> findALlByStatus(Status status);

}
