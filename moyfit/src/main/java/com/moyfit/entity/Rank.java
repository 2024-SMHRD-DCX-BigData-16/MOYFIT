package com.moyfit.entity;

import java.sql.Timestamp;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;



@AllArgsConstructor //모든 필드 생성자
@NoArgsConstructor //기본 생성자
@RequestMapping // getter/setter 메서드
@Getter
@Setter
@ToString //객체의 상태를 출력
public class Rank {

	 private int p_idx;
	 private String m_id;	
	 private String p_id;

	 private Double p_rating;

	    
}
	
	
	
	
	

	
	
	
	
	
	

