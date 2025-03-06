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

   // ȸ�� ���̵�
   @NonNull
   private String m_id;
   // ȸ�� ��й�ȣ
   @NonNull
   private String m_pw;
   // ȸ�� �г���
   private String m_nick;
   // ȸ�� ����
   private String m_gender;
   // ȸ�� �������
   private int m_age;
   // ȸ�� �ּ�
   private String m_addr;
   // ȸ�� �Ұ�
   private String m_info;
   // ȸ�� ����
   private String m_photo;
   // ȸ�� �����
   private String m_sport;
   // ȸ�� ����
   private String m_level;
   // ȸ�� ����
   private String m_status;
   // ���� ����
   private Timestamp joined_at;
   // ȸ�� ����
   private String m_role;
   // ����
   private double m_lat;
   // �浵
   private double m_lon;
   // �Ÿ�
   private double distance;

}
