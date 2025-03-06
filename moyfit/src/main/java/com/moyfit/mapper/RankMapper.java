package com.moyfit.mapper;

import java.util.List; 
import org.apache.ibatis.annotations.Mapper;
import com.moyfit.entity.Rank; 
import com.moyfit.entity.Party; 


@Mapper //SQL 문과 연결
public interface RankMapper {
 

    // 전체 랭킹 조회 (두 테이블의 데이터를 합친 결과)
    public List<Rank> getAllRankings();
    
    // 파티 랭킹 조회 (tb_party_rating 테이블만 조회)
    public List<Rank> gatherRankings();
    
    // 파트너 랭킹 조회 (tb_partner_rating 테이블만 조회)
    public List<Rank> partnerRankings();
    
    public List<Party> selectPartyName();
    
}