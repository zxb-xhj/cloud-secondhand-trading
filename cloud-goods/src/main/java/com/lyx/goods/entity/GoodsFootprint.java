package com.lyx.goods.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName gms_goods_footprint
 */
@TableName(value ="gms_goods_footprint")
@Data
public class GoodsFootprint implements Serializable {
    /**
     * 浏览id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 浏览时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}