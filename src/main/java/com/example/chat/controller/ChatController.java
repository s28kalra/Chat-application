package com.example.chat.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.chat.dto.ChatNotification;
import com.example.chat.model.ChatMessage;
import com.example.chat.service.impl.ChatMessageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {

	private final ChatMessageService chatMessageService;
	private final SimpMessagingTemplate messagingTemplate;

	@GetMapping("/messages/{senderId}/{recipientId}")
	public List<ChatMessage> findChatMessages(@PathVariable String senderId, @PathVariable String recipientId) {
		return chatMessageService.findChatMessages(senderId, recipientId);
	}
	
	@MessageMapping("/chat")
	public void processMessage(@Payload ChatMessage chatMessage) {
		chatMessage = chatMessageService.saveChatMessage(chatMessage);
		messagingTemplate.convertAndSendToUser(chatMessage.getRecipientId(), "/queue/messages",
				ChatNotification.builder().chatRoomId(chatMessage.getChatId()).senderId(chatMessage.getSenderId())
						.recipientId(chatMessage.getRecipientId()).content(chatMessage.getContent()).build());

	}

}
