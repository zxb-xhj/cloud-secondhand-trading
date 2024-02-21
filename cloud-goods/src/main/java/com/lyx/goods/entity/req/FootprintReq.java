package com.lyx.goods.entity.req;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * @Author: xhj
 * @Date: 2024/02/11/22:08
 * @Description:
 */
@Data
public class FootprintReq implements Serializable {

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 时间
     */
    private String createTime;

}
