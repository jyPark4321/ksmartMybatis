package ksmart.mybatis.goods.controller;

import ksmart.mybatis.goods.dto.Goods;
import ksmart.mybatis.goods.mapper.GoodsMapper;
import ksmart.mybatis.goods.service.GoodsService;
import ksmart.mybatis.member.dto.Member;
import ksmart.mybatis.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsService goodsService;
    private final MemberMapper memberMapper;

    @PostMapping("/addGoods")
    public String addGoods(Goods goods){
        goodsService.addGoods(goods);
        return "redirect:/goods/goodsList";
    }

    @GetMapping("/addGoods")
    public String addGoods(Model model) {
        List<Member> sellerList = memberMapper.getSellerList();
        //log.info("판매자현황:{}", sellerList);

        model.addAttribute("title", "상품등록");
        model.addAttribute("sellerList", sellerList);

        return "admin/goods/addGoods";
    }

    @GetMapping("/goodsList")
    public String getGoodsList(Model model){
        List<Goods> goodsList = goodsService.getGoodsList();

        //log.info("goodsList: {}", goodsList);

        model.addAttribute("title","상품목록조회");
        model.addAttribute("goodsList", goodsList);

        return "admin/goods/goodsList";

    }
}
