package com.moyfit.entity;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor // 모든 필드 생성자
@NoArgsConstructor // 기본 생성자
@RequestMapping // getter/setter 메서드
@Getter
@Setter
@ToString // 객체의 상태를 출력
public class Partner {

	// 파트너 고유번호
	private long partner_idx;
	// 제안자 아이디
	@NonNull // 해당 필드가 null이면 안 된다->정보 있어야함
	private String m_id;
	// 상대방 아이디
	@NonNull
	private String partner_id;
	// 수락 여부 Y / N
	private String accept_yn;
	// 제안 날짜를 저장
	private Timestamp created_at;
	public static List<Partner> getAllPartners() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setAvg_partner(double d) {
		// TODO Auto-generated method stub
		
	}

}
// <파트너 제안 정보>