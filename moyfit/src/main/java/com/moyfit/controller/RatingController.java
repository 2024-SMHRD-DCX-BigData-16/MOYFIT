package com.moyfit.controller;

import com.moyfit.entity.Partner;
import com.moyfit.entity.Party;
import com.moyfit.entity.PartnerRating;
import com.moyfit.entity.PartyRating;
import com.moyfit.entity.Rank;
import com.moyfit.mapper.PartnerMapper;
import com.moyfit.mapper.PartnerRatingMapper;
import com.moyfit.mapper.PartyRatingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class RatingController {

	@Autowired
	private PartnerRatingMapper partnerRatingMapper;

	@Autowired
	private PartyRatingMapper partyRatingMapper;

	@PostMapping("/submitRating")
	public String submitRating(
	        @RequestParam(value = "partner_id", required = false) String partner_id,
	        @RequestParam(value = "m_id", required = false) String m_id,
	        @RequestParam(value = "p_name", required = false) String p_name,
	        @RequestParam("rating") double rating) {

	    Date createdAt = new Date();
	    boolean partnerRated = false;
	    boolean partyRated = false;
	    try {
	    // ✅ 파트너 평가 저장
	    if (partner_id != null && !partner_id.trim().isEmpty()) {
	        PartnerRating partnerRating = new PartnerRating();
	        partnerRating.setM_id(partner_id);
	        partnerRating.setPartner_id(m_id);
	        partnerRating.setPartner_rating(rating);
	        partnerRating.setCreatedAt(createdAt);
	        partnerRatingMapper.insertRating(partnerRating);
	        partnerRated = true;
	    }

	    // ✅ 파티 평가 저장
	    if (p_name != null) {
	        PartyRating partyRating = new PartyRating();
	        partyRating.setP_name(p_name);
	        partyRating.setM_id(m_id);
	        partyRating.setP_rating(rating);
	        partyRating.setCreatedAt(createdAt);
	        int p_idx = partyRatingMapper.selectPartyName(p_name);
	        partyRating.setP_idx(p_idx);
	        partyRatingMapper.insertRating(partyRating);
	        partyRated = true;
	    }
	    }catch(Exception e) {
            // 외래키 제약 오류 발생 시 rating.jsp로 리다이렉트
	    	return "redirect:/rating?success=false";
        }

	    // ✅ 리디렉트 분기 처리 (올바른 페이지로 이동)
	    if (partnerRated) {
	        return "redirect:/partner_rating?success=true";
	    } else if (partyRated) {
	        return "redirect:/party_rating?success=true";
	    } else {
	    	return "redirect:/rating?success=false";
	    }
	}
	
////////선웅이가 이어서 할부분////////////////////////////
    //모든 파트너에 대한 평균 평점 조회
	@GetMapping("/partner_rate")
	public String getAllpartners(Model model) {
		List<PartnerRating> partnerRatings = partnerRatingMapper.getAllPartnerRankings();

	    System.out.println("🚀 partnerRatings 데이터 개수: " + partnerRatings.size());
	    for (PartnerRating partner : partnerRatings) {
	        System.out.println("✅ partner_id: " + partner.getPartner_id() + ", avg_partner: " + partner.getAvg_partner());
	    }

	    model.addAttribute("partnerRatings", partnerRatings);
	    return "partner"; 
	}

	//모든 파트너에 대한 평균 평점 조회
	@GetMapping("/party_rate")
	public String getAllPartys(Model model) {
	    List<PartyRating> partyRatings = partyRatingMapper.getAllPartyRankings();

	    System.out.println("🚀 partyRatings 데이터 개수: " + partyRatings.size());
	    for (PartyRating rating : partyRatings) {
	        System.out.println("✅ p_idx: " + rating.getP_idx() + ", avg_party: " + rating.getAvg_party());
	    }

	    model.addAttribute("partyRatings", partyRatings);
	    return "party";  
	}
	
	
}
