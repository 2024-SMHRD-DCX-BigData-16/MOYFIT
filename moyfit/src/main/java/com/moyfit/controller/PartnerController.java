
package com.moyfit.controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.moyfit.entity.Member;
import com.moyfit.entity.Partner;
import com.moyfit.entity.PartnerRating;
import com.moyfit.mapper.PartnerMapper;
import com.moyfit.mapper.PartnerRatingMapper;

@Controller
public class PartnerController {

	@Autowired
	private PartnerRatingMapper partnerRatingMapper;
	
	@Autowired
	private PartnerMapper partnerMapper;

	private double getDistance(double lat1, double lon1, double lat2, double lon2) {
		double R = 6371;
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return R * c;
	}

	@RequestMapping("/partner")
	public String partner(Member member, HttpSession session, Model model,
			@RequestParam(value = "m_sport", required = false) String m_sport) {
		Member loginUser = (Member) session.getAttribute("loginUser");
		String m_id = (String) loginUser.getM_id();
		List<Member> partnerList;
		
		List<PartnerRating> partnersRating = partnerRatingMapper.PartnersRating();
		System.out.println(partnersRating.toString());

		if (m_sport == null) {
			partnerList = partnerMapper.showPartner(m_id);
		} else {
			partnerList = partnerMapper.showSportPartner(m_sport, m_id);
		}

		double lat1 = loginUser.getM_lat();
		double lon1 = loginUser.getM_lon();

		for (int i = 0; i < partnerList.size(); i++) {
			member = partnerList.get(i);
			double lat2 = member.getM_lat();
			double lon2 = member.getM_lon();

			double dist = getDistance(lat1, lon1, lat2, lon2);
			member.setDistance(dist);
		}

		partnerList.sort(Comparator.comparingDouble(Member::getDistance));

		model.addAttribute("partnerList", partnerList);
		model.addAttribute("partnersRating", partnersRating);

		return "partner";
	}

	// 파트너 상세 정보 조회 메서드
	@RequestMapping("/partner_info")
	public String partner_info(@RequestParam("idx") String m_id, Model model) {
		Member partner_info = partnerMapper.showPartner_info(m_id);
		List<PartnerRating> partnersRating = partnerRatingMapper.PartnersRating();
		model.addAttribute("partner", partner_info);
		model.addAttribute("partnersRating", partnersRating);

		return "partner_info";
	}

	@GetMapping("/partner_rating")
	public String partnerRating(HttpSession session, Model model) {
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/login";
		}
		String m_id = loginUser.getM_id();
		try {
			// 수락된 파트너 정보 조회
			List<Partner> acceptedPartner = partnerMapper.selectAcceptedPartner(m_id);
			System.out.println(acceptedPartner.toString());
			model.addAttribute("acceptedPartner", acceptedPartner);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "파트너 정보를 불러오는 중 문제가 발생했습니다.");
		}
		return "partner_rating";
	}
	
	@PostMapping("/partnerOffer")
	public String partnerOffer(
	        @RequestParam("m_id") String m_id,
	        @RequestParam("partner_id") String partner_id,
	        RedirectAttributes redirectAttributes) {

	    if (m_id == null || partner_id == null || m_id.isEmpty() || partner_id.isEmpty()) {
	        redirectAttributes.addFlashAttribute("message", "잘못된 요청입니다.");
	        return "redirect:/partner";
	    }

	    try {
	        Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("m_id", m_id);
	        paramMap.put("partner_id", partner_id);

	        int result = partnerMapper.insertPartner(paramMap);

	        if (result > 0) {
	            redirectAttributes.addFlashAttribute("message", "운동 제의가 성공적으로 전송되었습니다.");
	        } else {
	            redirectAttributes.addFlashAttribute("message", "운동 제의 전송에 실패했습니다.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        redirectAttributes.addFlashAttribute("message", "서버 오류로 인해 요청이 실패했습니다.");
	    }

	    return "redirect:/partner"; // 다시 파트너 페이지로 이동
	}
	
	@RequestMapping(value = "/checkMatching", method = {RequestMethod.GET, RequestMethod.POST})
	public String checkMatching(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
	    Member loginUser = (Member) session.getAttribute("loginUser");
	    if (loginUser == null) {
	        redirectAttributes.addFlashAttribute("message", "로그인이 필요합니다.");
	        return "redirect:/login"; 
	    }
	    String m_id = loginUser.getM_id();
	    // ✅ 매칭 상태 가져오기
	    List<Partner> matching = partnerMapper.checkMatchingStatus(m_id);
	    model.addAttribute("matches", matching);
	    return "partner_match_status"; 
	}


	@PostMapping("/partner_accept")
	public String acceptPartnerRequest(
	        @RequestParam("m_id") String m_id,
	        @RequestParam("partner_id") String partner_id,
	        RedirectAttributes redirectAttributes) {

	    System.out.println("✅ 파트너 수락 요청 - 요청자: " + m_id + ", 상대방: " + partner_id);

	    int result = partnerMapper.acceptPartnerRequest(m_id, partner_id);
	    System.out.println("🔍 SQL 실행 결과 (영향 받은 행 수): " + result);

	    if (result > 0) {
	        redirectAttributes.addFlashAttribute("message", "파트너 요청을 수락했습니다.");
	        System.out.println("✅ 파트너 요청 수락 완료!");
	    } else {
	        redirectAttributes.addFlashAttribute("message", "이미 수락 완료된 요청입니다.");
	        System.out.println("❌ 수락 실패: 해당 매칭 요청 없음 또는 이미 승인됨.");
	    }

	    return "redirect:/partner_match_status";
	}


}
