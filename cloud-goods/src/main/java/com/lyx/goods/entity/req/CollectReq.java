package com.lyx.goods.entity.req;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: xhj
 * @Date: 2024/02/10/21:15
 * @Description:
 */
@Data
public class CollectReq implements Serializable {
    /**
     * 会员id
     */
    private Long memberId;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 添加 1/取消 2
     */
    private Integer state;

}
