package com.moyfit.mapper;

import com.moyfit.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMapper {

    // 내가 받은 메시지 조회
    List<Message> selectMessageReceive(@Param("receiver_id") String receiver_id);

    // 내가 보낸 메시지 조회
    List<Message> selectSentMessages(@Param("m_id") String m_id);

    // 특정 메시지 상세 조회
    Message selectMessageById(@Param("msg_idx") int msg_idx);

    // 메시지 읽음 처리
    void updateMessageRead(@Param("msg_idx") int msg_idx);
    
    // 특정 메시지 삭제
    void deleteMessageById(@Param("msg_idx") int msg_idx);

    // 특정 사용자의 모든 메시지 삭제
    int deleteAllMessagesByUser(@Param("receiver_id") String receiver_id);

    // 읽지 않은 메시지 개수 조회
    int countUnreadMessages(@Param("receiver_id") String receiver_id);

    // 모든 메시지 읽음 처리
    int updateAllMessagesRead(@Param("receiver_id") String receiver_id);

    // 메시지 전송
    void insertMessage(Message message);
}
