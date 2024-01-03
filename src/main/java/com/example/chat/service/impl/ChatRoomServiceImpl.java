package com.example.chat.service.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.chat.model.ChatRoom;
import com.example.chat.repository.ChatRoomRepository;
import com.example.chat.service.ChatRoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

	private final ChatRoomRepository chatRoomRepository;

	@Override
	public Optional<String> getChatRoomId(String senderId, String recipientId, boolean createNewRoomIfNotExists) {

		return chatRoomRepository.findFirstBySenderIdAndRecipientId(senderId, recipientId).map(ChatRoom::getChatId)
				.or(() -> {
					if (createNewRoomIfNotExists) {
						return createChatId(senderId, recipientId);
					}
					return Optional.empty();
				});
	}

	private Optional<String> createChatId(String senderId, String recipientId) {
		String chatId = String.format("%s_%s", senderId, recipientId);
		ChatRoom senderRecipient = ChatRoom.builder().senderId(senderId).recipientId(recipientId).chatId(chatId)
				.build();
		ChatRoom recipientSender = ChatRoom.builder().senderId(recipientId).recipientId(senderId).chatId(chatId)
				.build();
		chatRoomRepository.saveAll(Arrays.asList(senderRecipient, recipientSender));

		return Optional.of(chatId);
	}
}
