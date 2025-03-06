package com.moyfit.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.moyfit.entity.Member;
import com.moyfit.entity.Partner;
import com.moyfit.entity.PartnerRating;

import lombok.NonNull;

@Mapper
public interface PartnerMapper {

	public List<Member> showPartner(@Param("m_id") String m_id);

	public List<Member> showSportPartner(@Param("m_sport") String m_sport, @Param("m_id") String m_id);

	public Member showPartner_info(@Param("m_id") String m_id);

	public int suggestionPartner(@Param("partner_id") String partner_id, @Param("m_id") String m_id);

	public List<Partner> selectAcceptedPartner(@Param("m_id") String m_id);
	
	 int insertPartner(Map<String, Object> paramMap);
	 
	 List<Partner> checkMatchingStatus(@Param("m_id") String m_id);
 
	 int acceptPartnerRequest(@Param("m_id") String m_id, @Param("partner_id") String partner_id);



}
