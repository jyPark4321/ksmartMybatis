package ksmart.mybatis.goods.controller;

import ksmart.mybatis.goods.dto.Goods;
import ksmart.mybatis.goods.mapper.GoodsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsMapper goodsMapper;

    @GetMapping("/goodsList")
    public String getGoodsList(Model model){
        List<Goods> goodsList = goodsMapper.getGoodsList();

        log.info("goodsList: {}", goodsList);

        model.addAttribute("title","상품목록조회");
        model.addAttribute("goodsList", goodsList);

        return "admin/goods/goodsList";

    }
}
