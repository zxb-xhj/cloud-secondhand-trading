package com.lyx.admin.ser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author： xhj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role_permission")
public class SysRolePermission {
    private Long permissionId;
    private Long roleId;
}
