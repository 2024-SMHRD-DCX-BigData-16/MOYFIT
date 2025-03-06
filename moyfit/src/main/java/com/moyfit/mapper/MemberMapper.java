package com.moyfit.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.moyfit.entity.Member;

@Mapper
public interface MemberMapper {

   public void memberInsert(Member member);

   public Member memberLogin(Member member);

   // 회원탈퇴
   public void memberDelete(String m_id);

   // 프로필 업데이트
   public void profil_update(Member member);
   
   // 아이디 중복 체크
   public boolean idCheck(String m_id);

   // 닉네임 중복 체크
   public boolean nickCheck(String m_nick);


}