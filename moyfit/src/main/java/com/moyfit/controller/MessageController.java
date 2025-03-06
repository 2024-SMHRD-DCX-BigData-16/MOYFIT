package com.moyfit.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.moyfit.entity.Member;
import com.moyfit.entity.Message;
import com.moyfit.mapper.MessageMapper;

@Controller
public class MessageController {

    @Autowired
    private MessageMapper messageMapper;

    // 📌 ✅ 로그인 사용자 자동 로드
    @ModelAttribute("loginUser")
    public Member getLoginUser(HttpSession session) {
        Member loginUser = (Member) session.getAttribute("loginUser");
        if (loginUser == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        return loginUser;
    }

    // 📌 ✅ 내가 받은 & 보낸 메시지 목록 조회
    @GetMapping("/message")
    public String listNotifications(Model model, @ModelAttribute("loginUser") Member loginUser) {
        List<Message> messages = messageMapper.selectMessageReceive(loginUser.getM_id());
        List<Message> sentMessages = messageMapper.selectSentMessages(loginUser.getM_id());

        model.addAttribute("messages", messages);
        model.addAttribute("sentMessages", sentMessages);
        return "message"; 
    }

    // 📌 ✅ 메시지 읽음 처리
    @GetMapping("/message/read")
    public String markAsRead(@RequestParam("msg_idx") int msg_idx, @ModelAttribute("loginUser") Member loginUser) {
        messageMapper.updateMessageRead(msg_idx);
        return "redirect:/message";
    }

    // 📌 ✅ 메시지 상세 조회
    @GetMapping("/message/detail")
    public String messageDetail(@RequestParam("msg_idx") int msg_idx, Model model) {
        Message message = messageMapper.selectMessageById(msg_idx);
        if (message == null) {
            return "redirect:/message";
        }
        model.addAttribute("message", message);
        return "message_detail";
    }

    // 📌 ✅ 메시지 삭제 (내가 받은 메시지만 삭제 가능)
    @GetMapping("/message/delete")
    public String deleteMessage(@RequestParam("msg_idx") int msg_idx, @ModelAttribute("loginUser") Member loginUser) {
        Message message = messageMapper.selectMessageById(msg_idx);
        
        if (message == null || !message.getReceiver_id().equals(loginUser.getM_id())) {
            return "redirect:/message";
        }

        messageMapper.deleteMessageById(msg_idx);
        return "redirect:/message";
    }
    
 // 📌 ✅ 모든 메시지 읽음 처리 (AJAX 요청 처리)
    @PostMapping("/message/read-all")
    @ResponseBody
    public ResponseEntity<String> markAllMessagesAsRead(@ModelAttribute("loginUser") Member loginUser) {
        messageMapper.updateAllMessagesRead(loginUser.getM_id());
        return ResponseEntity.ok("✅ 모든 메시지가 읽음 처리되었습니다.");
    }
    
 // 📌 ✅ 모든 메시지 삭제 (AJAX 요청 처리)
    @PostMapping("/message/deleteAll")
    @ResponseBody
    public ResponseEntity<String> deleteAllMessages(@ModelAttribute("loginUser") Member loginUser) {
        int deletedCount = messageMapper.deleteAllMessagesByUser(loginUser.getM_id());
        return ResponseEntity.ok("✅ " + deletedCount + "개의 메시지가 삭제되었습니다.");
    }

    
   
    @PostMapping("/message/send")
    public String sendMessage(@RequestParam(value = "receiver_id", required = false) String receiver_id,
                              @RequestParam(value = "msg_content", required = false) String msg_content,
                              @ModelAttribute("loginUser") Member loginUser) {

        if (receiver_id == null || receiver_id.trim().isEmpty()) {
            return "redirect:/message_create?error=receiver_id_missing";
        }

        if (msg_content == null || msg_content.trim().isEmpty()) {
            return "redirect:/message_create?error=msg_content_missing";
        }

        Message message = new Message();
        message.setM_id(loginUser.getM_id());
        message.setReceiver_id(receiver_id);
        message.setMsg_content(msg_content);

        messageMapper.insertMessage(message);

        return "redirect:/message";
    }


    // 📌 ✅ 읽지 않은 메시지 개수 조회 (AJAX 응답)
    @GetMapping("/message/unread-count")
    @ResponseBody
    public ResponseEntity<Integer> getUnreadMessageCount(@ModelAttribute("loginUser") Member loginUser) {
        int unreadCount = messageMapper.countUnreadMessages(loginUser.getM_id());
        return ResponseEntity.ok(unreadCount);
    }
    
    
	
	
	@RequestMapping("/message_create")
	public String party_Send_Message(@RequestParam(value="idx", required = false) String m_id, Model model) {
		System.out.println(m_id);
		
		
		model.addAttribute("m_id", m_id);
		
		return "/message_create";
	}
}
