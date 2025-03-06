package com.moyfit.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;


@AllArgsConstructor  
@NoArgsConstructor   
@Getter
@Setter
@ToString    
public class PartyRating {
    
    private Integer p_idx;
    private String p_name;
    private String m_id;
    private Double p_rating;
    private Date createdAt;
    
    private Double avg_party;

	
}
	
	
	
	

	
	
	
	
	
	

