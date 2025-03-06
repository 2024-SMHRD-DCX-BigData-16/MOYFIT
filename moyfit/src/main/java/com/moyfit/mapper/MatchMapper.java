package com.moyfit.mapper;


import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import com.moyfit.entity.Match;
import lombok.NonNull;

@Mapper
public interface MatchMapper {

	 
	List<Match> pcheckMatchingStatus(String m_id);

	int acceptPartyRequest(@Param("m_id") String m_id, @Param("partner_id") String partner_id);


}

