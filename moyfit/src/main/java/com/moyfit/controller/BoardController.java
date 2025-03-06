package com.moyfit.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.moyfit.entity.Board;
import com.moyfit.entity.Member;
import com.moyfit.mapper.BoardMapper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class BoardController {

	@Autowired
	private BoardMapper boardMapper;

	@RequestMapping("/board")
	public String board(Model model, HttpSession session) {
		List<Board> b_list = boardMapper.boardList();
		model.addAttribute("boardList", b_list);
		return "board";
	}

	// board_write로
	@RequestMapping("/board_write")
	public String board_write(HttpSession session, HttpServletRequest request, Board board, Model model) {
		Member loginUser = (Member) session.getAttribute("loginUser");

		if (loginUser != null) {
			return "board_write";
		} else {
			return "redirect:/board"; // 로그인 안 된 상태 → 게시판으로 이동
		}
	}

	// 커뮤니티 글쓰기
	@RequestMapping("/board_insert")
	public String board_insert(HttpServletRequest request, HttpSession session) {
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

			String b_category = multi.getParameter("b_category");
			String b_title = multi.getParameter("b_title");
			String b_content = multi.getParameter("b_content");
			String b_file = multi.getFilesystemName("b_file");

			Member loginUser = (Member) session.getAttribute("loginUser");
			String m_id = loginUser.getM_id();

			Board board = new Board(b_title, b_content, b_file, b_category, m_id);
//			System.out.println(board);
			boardMapper.board_insert(board);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/board";
	}

	// 커뮤니티 디테일
	@RequestMapping("/board_detail")
	public String board_detail(HttpSession session, @RequestParam("idx") Integer idx, Model model) {

		if (idx != null) {
			int b_idx = idx;
			boardMapper.b_views_update(b_idx);
			Board boardDetail = boardMapper.id_select(b_idx);
//			System.out.println(boardDetail);
			session.setAttribute("boardDetail", boardDetail);
			return "board_detail";

		} else {
			return "redirect:/board";
		}

	}

	// 보드 수정
	@RequestMapping("/modify")
	public String modify(HttpSession session, @RequestParam(value = "idx", required = false) Integer idx) {

		Member loginUser = (Member) session.getAttribute("loginUser");
		String m_id = loginUser.getM_id();
		int b_idx = idx;
//		System.out.println(b_idx);
		return "board_update";
	}

	// 보드 업데이트
	@RequestMapping("/board_update")
	public String board_update(HttpServletRequest request, HttpSession session) {
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
			String b_category = multi.getParameter("b_category");
			String b_title = multi.getParameter("b_title");
			String b_content = multi.getParameter("b_content");
			String b_file = multi.getFilesystemName("b_file");
			Member loginUser = (Member) session.getAttribute("loginUser");
			Board boardDetail = (Board) session.getAttribute("boardDetail");
			System.out.println(loginUser);
			int b_idx = boardDetail.getB_idx();
			System.out.println(b_idx);
			String m_id = loginUser.getM_id();
			Board board = new Board(b_idx, b_title, b_content, b_file, b_category, m_id);
			System.out.println(board);
			boardMapper.board_update(board);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "redirect:/board";
	}

	// 보드 삭제
	@RequestMapping("/board_delete")
	public String board_delete(HttpSession session) {
		Board boardDetail = (Board) session.getAttribute("boardDetail");
		int b_idx = boardDetail.getB_idx();
//		System.out.println(b_idx);
//		System.out.println("boardDetail" + boardDetail);
		boardMapper.board_delete(b_idx);
		return "redirect:/board";
	}
}
