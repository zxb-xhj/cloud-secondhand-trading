package com.lyx.goods.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: xhj
 * @Date: 2024/02/10/21:12
 * @Description:
 */
@Data
public class CollectVO implements Serializable {
    private Integer id;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品图片
     */
    private String defaultImg;
    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 收藏时间
     */
    private LocalDateTime createTime;
    /**
     * 是否上架
     */
    private Integer isOnSell;

}
