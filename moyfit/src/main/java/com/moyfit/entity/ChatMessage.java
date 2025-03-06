package com.moyfit.entity; // 또는 적절한 패키지 (예: com.moyfit.model)

public class ChatMessage {
	private String sender;
	private String content;
	private String timestamp;

	// 기본 생성자, getter, setter, 생성자
	public ChatMessage() {
	}

	public ChatMessage(String sender, String content, String timestamp) {
		this.sender = sender;
		this.content = content;
		this.timestamp = timestamp;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}