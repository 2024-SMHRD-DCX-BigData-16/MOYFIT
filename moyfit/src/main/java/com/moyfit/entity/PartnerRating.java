package com.moyfit.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@AllArgsConstructor  // 모든 필드 생성자
@NoArgsConstructor   // 기본 생성자
@Getter
@Setter
@ToString           // 객체 상태 출력
public class PartnerRating {
    private String m_id;         // 평가자 ID
    private String partner_id;   // 평가 대상 ID
    private Double partner_rating;
    private Date createdAt = new Date(); 
    
    private Double avg_partner;
}
	
	
	
	
	

	
	
	
	
	
	

