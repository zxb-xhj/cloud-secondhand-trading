package com.lyx.member.entity.vo;

import com.lyx.member.entity.School;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: xhj
 * @Date: 2024/02/09/19:24
 * @Description:
 */
@Data
public class SchoolVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private List<SchoolVO> schoolVOS;

    private String addr;
}
