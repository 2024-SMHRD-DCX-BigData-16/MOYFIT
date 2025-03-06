package com.moyfit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.moyfit.entity.Board;
import com.moyfit.entity.Member;

@Mapper
public interface BoardMapper {
   
   public int board_insert(Board board);

   // ���� �� ��������, ���� �ҷ�����
   public List<Board> boardList();

   // �Խñ� ���̵� ���� ��������
   public Board id_select(int b_idx);

   // board update
   public void board_update(Board board);

   // ���� ����
   public void board_delete(int b_idx);

   // ��ȸ��
   public void b_views_update(int b_idx);
   
}