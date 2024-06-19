package ksmart.mybatis.goods.mapper;

import ksmart.mybatis.goods.dto.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {
    //상품등록
    int addGoods(Goods goods);
    // 상품조회
    List<Goods> getGoodsList();

    //판매자가 등록한 상품 삭제
    int removeGoodsBySellerId(String sellerId);
}
