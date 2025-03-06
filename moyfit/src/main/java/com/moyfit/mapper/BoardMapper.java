package com.moyfit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.moyfit.entity.Board;
import com.moyfit.entity.Member;

@Mapper
public interface BoardMapper {
   
   public int board_insert(Board board);

   // 보드 값 가져오기, 보드 불러오기
   public List<Board> boardList();

   // 게시글 아이디 정보 가져오기
   public Board id_select(int b_idx);

   // board update
   public void board_update(Board board);

   // 보드 삭제
   public void board_delete(int b_idx);

   // 조회수
   public void b_views_update(int b_idx);
   
}