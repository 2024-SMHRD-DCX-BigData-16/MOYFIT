package com.moyfit.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.moyfit.entity.Member;
import com.moyfit.entity.Partner;
import com.moyfit.entity.Party;
import lombok.NonNull;

@Mapper // SQL 문과 연결
public interface PartyMapper {

	public List<Party> selectAcceptedParty(String m_id);

	public List<Party> showParty(@Param("m_id") String m_id);

	public List<Party> showSportParty(@Param("p_sport") String p_sport, @Param("m_id") String m_id);

	public void insertParty(Party party);

	public Party party_info(int p_idx);
	
	public boolean nameCheck(String p_name);
	
	
	//

	// 파티 요청 저장
    
	 int insertPartys(Map<String, Object> paramMap);
	 
	 List<Match> pcheckMatchingStatus(@Param("partner_id") String partnerId, @Param("m_id") String mId);

	 int acceptPartyRequest(@Param("m_id") String mId);
}

