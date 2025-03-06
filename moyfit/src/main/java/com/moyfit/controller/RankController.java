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
   // ��Ʈ�� ��� ��ȸ, ��Ʈ�� �� ���� Ȯ��, � ���� ��

   @Autowired
   private RankMapper rankMapper;

   // ��ü ��ŷ ��ȸ (�� ���̺��� �����͸� ��ģ ���)
   @GetMapping("/getAllRankings")
   public String getAllRankings(Model model) {
      List<Party> partyList = rankMapper.selectPartyName();
      List<Rank> rankings = rankMapper.getAllRankings();

      System.out.println(partyList.toString());
      System.out.println(rankings.toString());

      // Party ������ Map���� ��ȯ (p_idx -> p_name ����)
      Map<String, String> partyMap = partyList.stream()
            .collect(Collectors.toMap(p -> String.valueOf(p.getP_idx()), Party::getP_name));

      // ��ŷ ����Ʈ ������Ʈ (�Ҽ��� �� �ڸ����� �ݿø�)
      for (Rank r : rankings) {
         if (partyMap.containsKey(r.getP_id())) {
            r.setP_id(partyMap.get(r.getP_id()));
         }
         // ���� �Ҽ��� �� �ڸ����� �ݿø�
         r.setP_rating(roundToTwoDecimalPlaces(r.getP_rating()));
      }

      model.addAttribute("rankings", rankings);
      model.addAttribute("title", "��ü ��ŷ");
      return "rank";
   }

   // �Ҽ��� �� �ڸ� �ݿø� �Լ�
   private double roundToTwoDecimalPlaces(double value) {
      return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
   }

   // ��Ƽ ��ŷ ��ȸ (tb_party_rating��)
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
      model.addAttribute("title", "��Ƽ ��ŷ");
      return "rank";
   }

   // ��Ʈ�� ��ŷ ��ȸ (tb_partner_rating��)
   @GetMapping("/getPartnerRankings")
   public String getPartnerRankings(Model model) {
       List<Rank> rankings = rankMapper.partnerRankings();

       // �Ҽ��� �� �ڸ����� �ݿø�
       rankings.forEach(r -> r.setP_rating(roundToTwoDecimalPlaces(r.getP_rating())));

       model.addAttribute("rankings", rankings);
       model.addAttribute("title", "��Ʈ�� ��ŷ");
       return "rank";
   }

   // �Ҽ��� �� �ڸ� �ݿø� �Լ�
   private double roundToTwoDecimalPlaces(Double value) {
       return new BigDecimal(value)
           .setScale(2, RoundingMode.HALF_UP)
           .doubleValue();
   }
}