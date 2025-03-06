package com.moyfit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.moyfit.entity.Match;
import com.moyfit.mapper.MatchMapper;

@Controller
public class MatchController {

    @Autowired
    private MatchMapper matchMapper;

    @RequestMapping("/match_status")
    public String showMatchStatus(@RequestParam(required = false) String m_id, Model model) {
        System.out.println("🔍 매칭 상태 페이지 요청: m_id = " + m_id); // ✅ 시스템 로그 추가
        List<Match> matches = matchMapper.pcheckMatchingStatus(m_id); // partner_id는 null 
        System.out.println("🔍 매칭된 데이터 개수: " + (matches != null ? matches.size() : 0)); // ✅ 데이터 확인 로그
        model.addAttribute("matches", matches);
        return "party_match_status"; // match_status.jsp 반환
    }


    @PostMapping("/party_accept")
    public String acceptPartyRequest(
            @RequestParam("m_id") String m_id,
            @RequestParam("partner_id") String partner_id,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        System.out.println("✅ 매칭 수락 요청 - 요청자: " + m_id + ", 상대방: " + partner_id);

        int result = matchMapper.acceptPartyRequest(m_id, partner_id);

        if (result > 0) {
            redirectAttributes.addFlashAttribute("message", "운동 제의를 수락했습니다.");
            System.out.println("✅ 매칭 수락 완료!");
        } else {
            redirectAttributes.addFlashAttribute("message", "수락 실패: 요청이 존재하지 않거나 이미 수락됨.");
            System.out.println("❌ 매칭 수락 실패.");
        }

        return "party_match_status"; // 매칭 상태 페이지로 리디렉트
    }
    }

    

