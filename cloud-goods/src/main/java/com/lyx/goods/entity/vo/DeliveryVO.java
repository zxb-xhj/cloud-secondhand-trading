package com.lyx.goods.entity.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author: xhj
 * @Date: 2024/02/14/22:56
 * @Description:
 */
@Data
public class DeliveryVO {
    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 0 是 ，1 否
     */
    private Integer isCoeducation;

    /**
     * 1 自提 ，2 快递 ，0 自提+快递
     */
    private Integer deliveryMethod;

    /**
     * 运费 0包邮
     */
    private Long freight;

    /**
     * 学校
     */
    private String schoolName;

    /**
     * 详细地址
     */
    private String addr;
}
