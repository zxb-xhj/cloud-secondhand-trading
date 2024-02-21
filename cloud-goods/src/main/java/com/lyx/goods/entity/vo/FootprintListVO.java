package com.lyx.goods.entity.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: xhj
 * @Date: 2024/02/12/15:21
 * @Description:
 */
@Data
public class FootprintListVO {
    private LocalDate createTime;
    private String goods_id;
}
