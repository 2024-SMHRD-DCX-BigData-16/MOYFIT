package com.moyfit.mapper;
import com.moyfit.entity.PartnerRating;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PartnerRatingMapper {
    int insertRating(PartnerRating partnerRating);
    
    List<PartnerRating> getAllPartnerRankings();
    
    List<PartnerRating> PartnersRating();
    
}