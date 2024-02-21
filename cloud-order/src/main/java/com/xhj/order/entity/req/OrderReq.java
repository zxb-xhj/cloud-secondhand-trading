package com.xhj.order.entity.req;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: xhj
 * @Date: 2023/09/07/14:38
 * @Description:
 */
@Data
public class OrderReq {
    private Long goodsId;
    private Long memberId;
    private Long addrId;
    private Integer deliveryMethod;
    private BigDecimal price;
    private BigDecimal freight;
    private String memberName;
    private String token;
    private String area;
    private String province;
    private String city;
    private String addr;
    private String consignee;
    private String mobile;
}
