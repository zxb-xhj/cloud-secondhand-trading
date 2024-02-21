package com.lyx.member.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: xhj
 * @Date: 2023/11/06/20:03
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 学校
     */
    private String schoolName;
}
