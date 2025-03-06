package com.moyfit.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.moyfit.entity.Rank;
import com.moyfit.entity.Party;
import com.moyfit.mapper.RankMapper;

@Controller
public class RankController {
   // 파트너 목록 조회, 파트너 상세 정보 확인, 운동 제의 등

   @Autowired
   private RankMapper rankMapper;

   // 전체 랭킹 조회 (두 테이블의 데이터를 합친 결과)
   @GetMapping("/getAllRankings")
   public String getAllRankings(Model model) {
      List<Party> partyList = rankMapper.selectPartyName();
      List<Rank> rankings = rankMapper.getAllRankings();

      System.out.println(partyList.toString());
      System.out.println(rankings.toString());

      // Party 정보를 Map으로 변환 (p_idx -> p_name 매핑)
      Map<String, String> partyMap = partyList.stream()
            .collect(Collectors.toMap(p -> String.valueOf(p.getP_idx()), Party::getP_name));

      // 랭킹 리스트 업데이트 (소수점 두 자리까지 반올림)
      for (Rank r : rankings) {
         if (partyMap.containsKey(r.getP_id())) {
            r.setP_id(partyMap.get(r.getP_id()));
         }
         // 별점 소수점 두 자리까지 반올림
         r.setP_rating(roundToTwoDecimalPlaces(r.getP_rating()));
      }

      model.addAttribute("rankings", rankings);
      model.addAttribute("title", "전체 랭킹");
      return "rank";
   }

   // 소수점 두 자리 반올림 함수
   private double roundToTwoDecimalPlaces(double value) {
      return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
   }

   // 파티 랭킹 조회 (tb_party_rating만)
   @GetMapping("/getPartyRankings")
   public String getPartyRankings(Model model) {
      List<Party> party = rankMapper.selectPartyName();
      List<Rank> rankings = rankMapper.gatherRankings();

      for (Rank r : rankings) {
         for (Party p : party) {
            if (r.getP_id().equals(String.valueOf(p.getP_idx()))) {
               r.setP_id(p.getP_name());
               break;
            }
         }
      }

      model.addAttribute("rankings", rankings);
      model.addAttribute("title", "파티 랭킹");
      return "rank";
   }

   // 파트너 랭킹 조회 (tb_partner_rating만)
   @GetMapping("/getPartnerRankings")
   public String getPartnerRankings(Model model) {
       List<Rank> rankings = rankMapper.partnerRankings();

       // 소수점 두 자리까지 반올림
       rankings.forEach(r -> r.setP_rating(roundToTwoDecimalPlaces(r.getP_rating())));

       model.addAttribute("rankings", rankings);
       model.addAttribute("title", "파트너 랭킹");
       return "rank";
   }

   // 소수점 두 자리 반올림 함수
   private double roundToTwoDecimalPlaces(Double value) {
       return new BigDecimal(value)
           .setScale(2, RoundingMode.HALF_UP)
           .doubleValue();
   }
}