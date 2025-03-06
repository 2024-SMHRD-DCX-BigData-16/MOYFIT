package com.moyfit.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.moyfit.entity.Board;
import com.moyfit.entity.Member;
import com.moyfit.mapper.MemberMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class MemberController {

	@Autowired
	private MemberMapper memberMapper;

	@RequestMapping("/")
	public String gohome() {
		return "home";
	}

	@GetMapping("/home")
	public String home() {
		return "home";
	}

	@RequestMapping("/login")
	public String login() {
		return "Login";
	}

	// 로그인
	@RequestMapping("/memberLogin")
	public String memberLogin(Member member, Model model, HttpSession session) {
		// DB 조회
		Member loginMember = memberMapper.memberLogin(member);
		// DB에서 가져온 결과 확인
		if (loginMember != null) {
			session.setAttribute("loginUser", loginMember);
			return "home";
		} else {
			session.removeAttribute("loginUser");
			return "Login";
		}
	}

	// 커뮤니티 글 등록
	@RequestMapping("/w_comm")
	public String w_comm() {
		return "board";
	}

	// 로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginUser");
		return "home";
	}

	// 회원가입 페이지로
	@RequestMapping("/memberJoin")
	public String memberJoin() {
		return "Join";
	}

	// 회원가입
	@PostMapping("/memberInsert")
	public String savePath(HttpServletRequest request, Member member) {
		MultipartRequest multi = null;
		try {
			// 업로드 디렉토리 설정 (웹 애플리케이션 내부 또는 외부 디스크)
			String savePath = request.getServletPath();
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
			member.setM_id(multi.getParameter("m_id"));
			member.setM_pw(multi.getParameter("m_pw"));
			member.setM_nick(multi.getParameter("m_nick"));
			member.setM_gender(multi.getParameter("m_gender"));
			member.setM_photo(multi.getFilesystemName("m_photo"));
			member.setM_age(Integer.parseInt(multi.getParameter("m_age")));
			member.setM_addr(multi.getParameter("m_addr"));
			member.setM_info(multi.getParameter("m_info"));
			member.setM_sport(multi.getParameter("m_sport"));
			member.setM_level(multi.getParameter("m_level"));
			member.setM_lat(Double.parseDouble(multi.getParameter("m_lat")));
			member.setM_lon(Double.parseDouble(multi.getParameter("m_lon")));

			// 데이터베이스에 회원 정보 저장
			memberMapper.memberInsert(member);

		} catch (NumberFormatException e) {
			System.out.println("숫자 변환 오류: " + e.getMessage());
			return "redirect:/login";
		} catch (IOException e) {
			System.out.println("파일 업로드 오류: " + e.getMessage());
			return "redirect:/login";
		} catch (Exception e) {
			System.out.println("기타 오류: " + e.getMessage());
			return "redirect:/login";
		}
		return "redirect:/login";
	}

	// 회원 탈퇴
	@RequestMapping("/memberDelete")
	public String memberDelete(HttpSession session) {
		Member loginMember = (Member) session.getAttribute("loginUser");
		String m_id = loginMember.getM_id();
		memberMapper.memberDelete(m_id);
		session.invalidate();
		return "home";
	}

	@RequestMapping("/rank")
	public String rank() {
		return "rank";
	}

	@RequestMapping("/qna")
	public String qna() {
		return "qna";
	}

	@RequestMapping("/qna_write")
	public String qna_write() {
		return "qna_write";
	}

	@RequestMapping("/rating")
	public String star() {
		return "rating";
	}

	// 파트너 프로필
	@RequestMapping("/partner_profil")
	public String partner_profil() {
		return "partner_profil";
	}

	
	// 파티 프로필
	@RequestMapping("/party_profil")
	public String party_profil() {
		return "party_profil";
	}

	// 요청
	@RequestMapping("/request")
	public String request() {
		return "request";
	}

	// 프로필 업데이트
	@PostMapping("/profil_update")
	public String profil_update(HttpServletRequest request, HttpSession session, Member member) {
		MultipartRequest multi = null;
		try {
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
			Member loginUser = (Member) session.getAttribute("loginUser");
			String m_id = loginUser.getM_id();

			member.setM_nick(multi.getParameter("m_nick"));
			member.setM_gender(multi.getParameter("m_gender"));
			member.setM_photo(multi.getFilesystemName("m_photo"));
			member.setM_age(Integer.parseInt(multi.getParameter("m_age")));
			member.setM_addr(multi.getParameter("m_addr"));
			member.setM_info(multi.getParameter("m_info"));
			member.setM_sport(multi.getParameter("m_sport"));
			member.setM_level(multi.getParameter("m_level"));
			member.setM_lat(Double.parseDouble(multi.getParameter("m_lat")));
			member.setM_lon(Double.parseDouble(multi.getParameter("m_lon")));
			member.setM_id(m_id);

			memberMapper.profil_update(member);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/home";
	}

	// 아이디 중복 체크
	@PostMapping("/idCheck")
	public @ResponseBody String idCheck(@RequestParam("m_id") String m_id) {
		System.out.println("아이디 중복 체크  : " + m_id);
		if (memberMapper.idCheck(m_id)) {
			System.out.println("true");
			return "true"; // ID 중복
		} else {
			System.out.println("false");
			return "false"; // ID 사용 가능
		}
	}

	// 닉네임 중복 체크
	@PostMapping("/nickCheck")
	public @ResponseBody String nickCheck(@RequestParam("m_nick") String m_nick) {
		System.out.println("닉네임 중복 체크  : " + m_nick);
		if (memberMapper.nickCheck(m_nick)) {
			System.out.println("true");
			return "true";
		} else {
			System.out.println("false");
			return "false";
		}
	}

	//
	@RequestMapping("/partner_match_status")
	public String partner_match_status() {
		return "partner_match_status";
	}

	@RequestMapping("/match")
	public String match() {
		return "match";
	}

}
