package com.moyfit.entity;

import java.sql.Timestamp;

public class Community {

	// 고유번호
	private long b_idx;
	
	// 카테고리
	private String b_category;
	
	// 제목
	private String b_title;
	
	// 내용
	private String b_content;
	
	// 사진
	private String b_file;
	
	// 작성 일자
	private Timestamp created_at;
	
	// 조회수
	private int b_views;
	
	// 좋아요수
	private int b_likes;
	
	// 작성자
	private String m_id;
	
}
