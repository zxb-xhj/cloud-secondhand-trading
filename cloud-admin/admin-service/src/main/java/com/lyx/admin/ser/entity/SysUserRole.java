package com.lyx.admin.ser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author： xhj
 */
@Data
@TableName("sys_user_role")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 角色id
    */
    private Long roleId;

    public SysUserRole() {}

    public SysUserRole(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}