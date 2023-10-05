package com.lyx.goods.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: xhj
 * @Date: 2023/09/28/0:00
 * @Description:
 */
@Data
public class RecommendVo {
    private Long id;

    /**
     * 物品名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 浏览量
     */
    private Integer viewCount;
}
