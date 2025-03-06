package com.moyfit.entity;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Message {
    private Integer msg_idx; // 
    private String m_id; // 메시지 발신자
    private String receiver_id; // 메시지 수신자
    private String msg_content; // 메시지 내용
    private Timestamp sended_at;
    private String receive_yn;
}
