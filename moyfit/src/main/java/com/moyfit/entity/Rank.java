package com.moyfit.entity;

import java.sql.Timestamp;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;



@AllArgsConstructor //��� �ʵ� ������
@NoArgsConstructor //�⺻ ������
@RequestMapping // getter/setter �޼���
@Getter
@Setter
@ToString //��ü�� ���¸� ���
public class Rank {

	 private int p_idx;
	 private String m_id;	
	 private String p_id;

	 private Double p_rating;

	    
}
	
	
	
	
	

	
	
	
	
	
	

