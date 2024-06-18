package ksmart.mybatis.goods.dto;

import ksmart.mybatis.member.dto.Member;
import lombok.Data;

@Data
public class Goods {
    private String goodsCode;
    private String goodsName;
    private int goodsPrice;
    private String goodsSellerId;
    private String goodsRegDate;

    //판매자 정보
    private Member sellerInfo;
}
