package com.moyfit.entity;

import java.sql.Timestamp;

import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@RequestMapping
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Board {

	private int b_idx;
	@NonNull
	private String b_title;
	@NonNull
	private String b_content;
	private String b_file;
	private Timestamp created_at;
	private int b_views;
	private int b_likes;
	@NonNull
	private String b_category;
	@NonNull
	private String m_id;

	// 커뮤 글쓰기
	public Board(@NonNull String b_title, @NonNull String b_content, String b_file, @NonNull String b_category,
			@NonNull String m_id) {
		super();
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_file = b_file;
		this.b_category = b_category;
		this.m_id = m_id;
	}

	// 보드 업데이트
	public Board(int b_idx, @NonNull String b_title, @NonNull String b_content, String b_file,
			@NonNull String b_category, @NonNull String m_id) {
		super();
		this.b_idx = b_idx;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_file = b_file;
		this.b_category = b_category;
		this.m_id = m_id;
	}

}
