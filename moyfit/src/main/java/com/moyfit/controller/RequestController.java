package com.moyfit.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.moyfit.entity.Member;
import com.moyfit.entity.Partner;
import com.moyfit.mapper.RequestMapper;
@Controller
public class RequestController {

    @Autowired
    private RequestMapper requestMapper;

    @RequestMapping("/partner_request")
    public String viewPartnerRequests(HttpSession session, Model model) {
        Member loginUser = (Member) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/Login";
        }

        String m_id = loginUser.getM_id();

        List<Partner> sentRequests = requestMapper.selectSentPartnerRequests(m_id);
        model.addAttribute("sentRequests", sentRequests);

        List<Partner> receivedRequests = requestMapper.selectReceivedPartnerRequests(m_id);
        model.addAttribute("receivedRequests", receivedRequests);

        return "partner_request";
    }
}
