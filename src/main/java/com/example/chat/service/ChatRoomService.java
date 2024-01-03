package com.example.chat.service;

import java.util.Optional;

public interface ChatRoomService {

	Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfExists);

}