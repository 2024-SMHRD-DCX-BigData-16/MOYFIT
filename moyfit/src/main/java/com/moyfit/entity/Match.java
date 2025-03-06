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
    private int match_idx;   // 매치 고유번호
    private String partner_id; // 상대방 아이디
    private String m_id;     // 지원자 아이디
    private String approve_yn; // 승인 여부
    private Timestamp created_at;  // 지원 날짜
}
