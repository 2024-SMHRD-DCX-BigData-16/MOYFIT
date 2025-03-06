package com.moyfit.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.moyfit.entity.Member;
import com.moyfit.entity.PartnerRating;
import com.moyfit.entity.Party;
import com.moyfit.entity.PartyRating;
import com.moyfit.entity.Rank;
import com.moyfit.mapper.PartyMapper;
import com.moyfit.mapper.PartyRatingMapper;
import com.moyfit.mapper.RankMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class PartyController {

	@Autowired
	private RankMapper rankMapper;

	@Autowired
	private PartyMapper partyMapper;

	@Autowired
	private PartyRatingMapper partyRatingMapper;

	private double getDistance(double lat1, double lon1, double lat2, double lon2) {
		double R = 6371;
		double dLat = Math.toRadians(lat2 - lat1);
		double dLon = Math.toRadians(lon2 - lon1);

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return R * c;
	}

	@RequestMapping("/party")
	public String party(Member member, HttpSession session, Model model,
			@RequestParam(value = "p_sport", required = false) String p_sport) {

		List<PartyRating> partysRating = partyRatingMapper.partysRating();

		Member loginUser = (Member) session.getAttribute("loginUser");
		String m_id = (String) loginUser.getM_id();
		List<Party> partyList;

		if (p_sport == null) {
			partyList = partyMapper.showParty(m_id);
		} else {
			partyList = partyMapper.showSportParty(p_sport, m_id);
		}

		double lat1 = loginUser.getM_lat();
		double lon1 = loginUser.getM_lon();

		for (int i = 0; i < partyList.size(); i++) {
			Party party = partyList.get(i);
			double lat2 = party.getP_lat();
			double lon2 = party.getP_lon();

			double dist = getDistance(lat1, lon1, lat2, lon2);

			party.setDistance(dist);
		}

		partyList.sort(Comparator.comparingDouble(Party::getDistance));

		model.addAttribute("PartyList", partyList);
		model.addAttribute("partysRating", partysRating);

		return "party";
	}

	// 모임 지원 평가 페이지 이동 (예: /party_rating)
	@GetMapping("/party_rating")
	public String partyRating(HttpSession session, Model model) {
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/login";
		}
		String m_id = loginUser.getM_id();

		try {
			// 로그인한 사용자의 수락된 모임 지원 정보를 조회
			List<Party> acceptedParty = partyMapper.selectAcceptedParty(m_id);
			System.out.println(acceptedParty.toString());
			model.addAttribute("acceptedParty", acceptedParty);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "모임 지원 정보를 불러오는 중 문제가 발생했습니다.");
		}

		return "party_rating";
	}

	// 모임 생성
	@GetMapping("/party_profil")
	public String party_profil() {
		return "party_profil";
	}


	@PostMapping("/insertParty")
	public String insertParty(Party party, HttpSession session, HttpServletRequest request) {
		MultipartRequest multi = null;
		try {
			// 업로드 디렉토리 설정 (웹 애플리케이션 내부 또는 외부 디스크)
			String savePath = request.getRealPath("/uploads");
			File uploadDir = new File(savePath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs(); // 디렉토리가 없으면 생성
			}
			int maxSize = 1024 * 1024 * 10; // 10MB
			String encoding = "UTF-8";
			DefaultFileRenamePolicy df = new DefaultFileRenamePolicy();
			// MultipartRequest 생성
			multi = new MultipartRequest(request, savePath, maxSize, encoding, df);
			// 폼 데이터 가져오기
			party.setM_id(multi.getParameter("m_id"));
			party.setP_name(multi.getParameter("p_name"));
			party.setP_addr(multi.getParameter("p_addr"));
			party.setP_info(multi.getParameter("p_info"));
			party.setP_photo(multi.getFilesystemName("p_photo"));
			party.setP_limit(Integer.parseInt(multi.getParameter("p_limit")));
			party.setP_sport(multi.getParameter("p_sport"));
			party.setP_lat(Double.parseDouble(multi.getParameter("p_lat")));
			party.setP_lon(Double.parseDouble(multi.getParameter("p_lon")));

			// 데이터베이스에 회원 정보 저장
			partyMapper.insertParty(party);

		} catch (NumberFormatException e) {
			System.out.println("숫자 변환 오류: " + e.getMessage());
			return "redirect:/party";
		} catch (IOException e) {
			System.out.println("파일 업로드 오류: " + e.getMessage());
			return "redirect:/party";
		} catch (Exception e) {
			System.out.println("기타 오류: " + e.getMessage());
			return "redirect:/party";
		}
		System.out.println(party.toString());
		return "redirect:/party";
	}

	// 모임명 중복체크
	@PostMapping("/nameCheck")
	public @ResponseBody String idCheck(@RequestParam("p_name") String p_name) {
		System.out.println("아이디 중복 체크  : " + p_name);
		if (partyMapper.nameCheck(p_name)) {
			System.out.println("true");
			return "true"; // 모임명 중복
		} else {
			System.out.println("false");
			return "false"; // 모임명 사용 가능
		}
	}

	@GetMapping("/party_info")
	public String party_info(@RequestParam(value = "idx", required = false) int p_idx, Model model) {
		List<PartyRating> partysRating = partyRatingMapper.partysRating();
		Party party_info = partyMapper.party_info(p_idx);
		model.addAttribute("party", party_info);
		model.addAttribute("partysRating", partysRating);

		return "party_info";
	}

	//////

	// 파티 요청 보내기
	@PostMapping("/partyOffer")
	public String partyOffer(@RequestParam("m_id") String m_id, @RequestParam("partner_id") String partner_id,
			RedirectAttributes redirectAttributes) {
		if (m_id == null || partner_id == null || m_id.isEmpty() || partner_id.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "잘못된 요청입니다.");
			return "redirect:/party";
		}

		try {
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("m_id", m_id);
			paramMap.put("partner_id", partner_id);

			int result = partyMapper.insertPartys(paramMap);

			if (result > 0) {
				redirectAttributes.addFlashAttribute("message", "운동 제의가 성공적으로 전송되었습니다.");
			} else {
				redirectAttributes.addFlashAttribute("message", "운동 제의 전송에 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "서버 오류로 인해 요청이 실패했습니다.");
		}

		return "redirect:/party";
	}

}
