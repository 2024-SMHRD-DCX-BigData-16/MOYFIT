package com.moyfit.mapper;
import com.moyfit.entity.PartnerRating;
import com.moyfit.entity.PartyRating;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



@Mapper
public interface PartyRatingMapper {
  
    int insertRating(PartyRating partyRating);
    
    int selectPartyName(String p_name);
    
    List<PartyRating> getAllPartyRankings();
    
    List<PartyRating> partysRating();

    
    
  
}