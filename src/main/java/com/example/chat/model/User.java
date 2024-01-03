package com.example.chat.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_tbl")
@Getter
@Setter
public class User {

	public enum Status {
		ONLINE, OFFLINE, TYPING
	}

	@Id
	private String nickName;
	private String fullName;
	private Status status;

}
