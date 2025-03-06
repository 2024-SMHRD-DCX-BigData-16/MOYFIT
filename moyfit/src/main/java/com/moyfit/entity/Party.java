package com.moyfit.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.NonFinal;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Party {

	// 모임 고유 번호
	private int p_idx;
	// 방장 아이디
	@NonNull
	private String m_id;
	// 모임 이름
	@NonNull
	private String p_name;
	// 모임지 주소
	@NonNull
	private String p_addr;
	// 모임지 위도
	private double p_lat;
	// 모임지 경도
	private double p_lon;
	// 모임 정보 소개
	private String p_info;
	// 모임 대표 사진
	private String p_photo;
	// 모임 참여자 제한 수
	@NonNull
	private int p_limit;
	// 모임 운동 종목
	@NonNull
	private String p_sport;
	// 모임 장소까지의 거리
	private double distance;
	// 수락 여부
	private char p_accepted;
	// 신청 수락된 인원
	private int p_personnel;

}
