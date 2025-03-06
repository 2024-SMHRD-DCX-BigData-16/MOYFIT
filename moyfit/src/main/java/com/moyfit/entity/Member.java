package com.moyfit.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@RequiredArgsConstructor
public class Member {

   // 회원 아이디
   @NonNull
   private String m_id;
   // 회원 비밀번호
   @NonNull
   private String m_pw;
   // 회원 닉네임
   private String m_nick;
   // 회원 성별
   private String m_gender;
   // 회원 생년월일
   private int m_age;
   // 회원 주소
   private String m_addr;
   // 회원 소개
   private String m_info;
   // 회원 사진
   private String m_photo;
   // 회원 운동종목
   private String m_sport;
   // 회원 레벨
   private String m_level;
   // 회원 상태
   private String m_status;
   // 가입 일자
   private Timestamp joined_at;
   // 회원 구분
   private String m_role;
   // 위도
   private double m_lat;
   // 경도
   private double m_lon;
   // 거리
   private double distance;

}
