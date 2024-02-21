package com.lyx.goods.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: xhj
 * @Date: 2024/02/09/15:34
 * @Description:
 */
@TableName(value ="gms_goods_delivery")
@Data
public class GoodsDelivery implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 商品id
     */
    @TableId
    private Long goodsId;

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