package com.moyfit.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.moyfit.entity.Member;

@Mapper
public interface MemberMapper {

   public void memberInsert(Member member);

   public Member memberLogin(Member member);

   // ȸ��Ż��
   public void memberDelete(String m_id);

   // ������ ������Ʈ
   public void profil_update(Member member);
   
   // ���̵� �ߺ� üũ
   public boolean idCheck(String m_id);

   // �г��� �ߺ� üũ
   public boolean nickCheck(String m_nick);


}