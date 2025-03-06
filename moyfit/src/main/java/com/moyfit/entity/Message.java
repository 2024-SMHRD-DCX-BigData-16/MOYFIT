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
    private String m_id; // �޽��� �߽���
    private String receiver_id; // �޽��� ������
    private String msg_content; // �޽��� ����
    private Timestamp sended_at;
    private String receive_yn;
}
