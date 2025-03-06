package com.moyfit.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@AllArgsConstructor  // ��� �ʵ� ������
@NoArgsConstructor   // �⺻ ������
@Getter
@Setter
@ToString           // ��ü ���� ���
public class PartnerRating {
    private String m_id;         // ���� ID
    private String partner_id;   // �� ��� ID
    private Double partner_rating;
    private Date createdAt = new Date(); 
    
    private Double avg_partner;
}
	
	
	
	
	

	
	
	
	
	
	

