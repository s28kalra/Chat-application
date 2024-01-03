package com.example.chat.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.chat.model.ChatMessage;
import com.example.chat.repository.ChatMessageRepository;
import com.example.chat.service.ChatRoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

	private final ChatMessageRepository chatMessageRepository;
	private final ChatRoomService chatRoomService;

	public ChatMessage saveChatMessage(ChatMessage chatMessage) {
		String chatRoomId = chatRoomService.getChatRoomId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true)
				.orElseThrow();
		chatMessage.setChatId(chatRoomId);
		return chatMessageRepository.save(chatMessage);
	}

	public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
		String chatRoomId = chatRoomService.getChatRoomId(senderId, recipientId, false).orElseThrow();
		return chatMessageRepository.findAllByChatIdOrderByTimestampDesc(chatRoomId);
	}

}
