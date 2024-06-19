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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsService goodsService;
    private final MemberMapper memberMapper;

    @PostMapping("/searchList")
    @ResponseBody
    public List<Goods> getGoodsSearchList(@RequestBody List<Map<String, Object>> searchList){
        // log.info("searchList : {}",searchList);
        List<Goods> goodsList = goodsService.getSearchList(searchList);
        log.info("goodsList: {}",goodsList);
        return null;
    }

    @GetMapping("/modifyGoods")
    public String modifyGoods(@RequestParam(value = "goodsCode") String goodsCode,Model model){
        log.info("상품수정화면 상품코드: {}",goodsCode);
        Goods goodsInfo = goodsService.getGoodsInfoByCode(goodsCode);
        List<Member> sellerList = memberMapper.getSellerList();
        log.info("특정코드기준 상품조회 : {}",goodsInfo);
        model.addAttribute("title","상품수정");
        model.addAttribute("goodsInfo",goodsInfo);
        model.addAttribute("sellerList", sellerList);
        return "admin/goods/modifyGoods";
    }

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
