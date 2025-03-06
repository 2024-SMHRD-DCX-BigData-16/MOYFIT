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
public class Match {
    private int match_idx;   // ��ġ ������ȣ
    private String partner_id; // ���� ���̵�
    private String m_id;     // ������ ���̵�
    private String approve_yn; // ���� ����
    private Timestamp created_at;  // ���� ��¥
}
