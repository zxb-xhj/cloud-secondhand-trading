package com.lyx.goods.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author: xhj
 * @Date: 2024/02/11/22:09
 * @Description:
 */
@Data
public class FootprintVO implements Serializable {
    /**
     * 浏览记录id
     */
    private Long footprintId;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品图片
     */
    private String goodsImage;
    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 浏览时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTime;
    /**
     * 是否上架
     */
    private Integer isOnSell;
}
