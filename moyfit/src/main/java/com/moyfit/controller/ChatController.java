package com.moyfit.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.moyfit.entity.ChatMessage;

@Controller
public class ChatController {

	@MessageMapping("/sendMessage")
	@SendTo("/topic/publicMessages")
	public ChatMessage sendMessage(ChatMessage message) {
		message.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		return message;
	}
}