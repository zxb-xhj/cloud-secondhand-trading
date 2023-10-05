package com.lyx.admin.ser.config;

import com.lyx.admin.ser.entity.SysPermission;
import com.lyx.admin.ser.entity.SysRole;
import com.lyx.admin.ser.entity.SysUser;
import com.lyx.admin.ser.entity.vo.SysPermissionVO;
import com.lyx.admin.ser.entity.vo.SysRoleVO;
import com.lyx.admin.ser.entity.vo.SysUserVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-26T19:38:54+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class AdminMapStructImpl implements AdminMapStruct {

    @Override
    public List<SysRoleVO> sysRoleToSysRoleVO(List<SysRole> sysRoles) {
        if ( sysRoles == null ) {
            return null;
        }

        List<SysRoleVO> list = new ArrayList<SysRoleVO>( sysRoles.size() );
        for ( SysRole sysRole : sysRoles ) {
            list.add( sysRoleToSysRoleVO1( sysRole ) );
        }

        return list;
    }

    @Override
    public List<SysUserVO> sysUser2SysUserVO(List<SysUser> sysUsers) {
        if ( sysUsers == null ) {
            return null;
        }

        List<SysUserVO> list = new ArrayList<SysUserVO>( sysUsers.size() );
        for ( SysUser sysUser : sysUsers ) {
            list.add( sysUserToSysUserVO( sysUser ) );
        }

        return list;
    }

    @Override
    public List<SysPermissionVO> sysPermission2SysPermissionVO(List<SysPermission> permissions) {
        if ( permissions == null ) {
            return null;
        }

        List<SysPermissionVO> list = new ArrayList<SysPermissionVO>( permissions.size() );
        for ( SysPermission sysPermission : permissions ) {
            list.add( sysPermissionToSysPermissionVO( sysPermission ) );
        }

        return list;
    }

    protected SysRoleVO sysRoleToSysRoleVO1(SysRole sysRole) {
        if ( sysRole == null ) {
            return null;
        }

        SysRoleVO sysRoleVO = new SysRoleVO();

        sysRoleVO.setId( sysRole.getId() );
        sysRoleVO.setName( sysRole.getName() );
        sysRoleVO.setCode( sysRole.getCode() );
        sysRoleVO.setSort( sysRole.getSort() );
        sysRoleVO.setStatus( sysRole.getStatus() );

        return sysRoleVO;
    }

    protected SysUserVO sysUserToSysUserVO(SysUser sysUser) {
        if ( sysUser == null ) {
            return null;
        }

        SysUserVO sysUserVO = new SysUserVO();

        sysUserVO.setId( sysUser.getId() );
        sysUserVO.setUsername( sysUser.getUsername() );
        sysUserVO.setNickname( sysUser.getNickname() );
        sysUserVO.setMobile( sysUser.getMobile() );
        sysUserVO.setGender( sysUser.getGender() );
        sysUserVO.setAvatar( sysUser.getAvatar() );
        sysUserVO.setEmail( sysUser.getEmail() );
        sysUserVO.setStatus( sysUser.getStatus() );
        List<Long> list = sysUser.getRoleIds();
        if ( list != null ) {
            sysUserVO.setRoleIds( new ArrayList<Long>( list ) );
        }

        return sysUserVO;
    }

    protected SysPermissionVO sysPermissionToSysPermissionVO(SysPermission sysPermission) {
        if ( sysPermission == null ) {
            return null;
        }

        SysPermissionVO sysPermissionVO = new SysPermissionVO();

        sysPermissionVO.setId( sysPermission.getId() );
        sysPermissionVO.setName( sysPermission.getName() );
        sysPermissionVO.setMenuId( sysPermission.getMenuId() );
        sysPermissionVO.setUrlPerm( sysPermission.getUrlPerm() );
        sysPermissionVO.setBtnSign( sysPermission.getBtnSign() );

        return sysPermissionVO;
    }
}
