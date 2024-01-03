package com.example.chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> exception(Exception e) {
		System.out.println("Ëxception: " + e.getStackTrace());
		return ResponseEntity.ok(null);
	}

}
