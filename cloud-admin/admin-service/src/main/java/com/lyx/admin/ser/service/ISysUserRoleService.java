package com.lyx.admin.ser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyx.admin.ser.entity.SysUserRole;

import java.util.List;

/**
 * @author： xhj
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    /**
     * 获取用户绑定的角色IDs
     * @param userId
     * @return
     */
    List<Long> selectRoleIds(Long userId);

    /**
     * 根据用户ID删除角色绑定关系
     * @param userId
     */
    void deleteByUserId(Long userId);
}
