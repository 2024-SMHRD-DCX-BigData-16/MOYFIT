package com.moyfit.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.moyfit.entity.Partner;

@Mapper
public interface RequestMapper {



    // ✅ 매칭 수락 상태 업데이트
    void updatePartnerStatus(@Param("partner_idx") int partner_idx);

    // ✅ 내가 보낸 매칭 요청 조회
    List<Partner> selectSentPartnerRequests(@Param("m_id") String m_id);

    // ✅ 내가 받은 매칭 요청 조회
    List<Partner> selectReceivedPartnerRequests(@Param("partner_id") String partner_id);
}
