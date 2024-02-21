package com.lyx.goods.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: xhj
 * @Date: 2024/02/12/14:34
 * @Description:
 */
@Data
public class FootprintMapVO {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createTime;
    private List<FootprintVO> footprintVOS;
}
