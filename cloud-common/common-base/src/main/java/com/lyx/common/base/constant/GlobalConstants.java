package com.lyx.common.base.constant;

/**
 * @author xhj
 * @date 2022年10月10日 17:25
 */
public class GlobalConstants {

    public static final String URL_PERM_ROLES_KEY = "system:perm_roles_rule:url:";
     /**
       * 开启状态
       */
    public static final Integer STATUS_ON = 1;
     /**
       * 关闭状态
       */
     public static final Integer STATUS_OFF = 0;
      /**
        * 默认密码
        */
     public static final String USER_DEFAULT_PASSWORD = "123456789";

      /**
        * 菜单根目录
        */
     public static final Long ROOT_MENU_ID = -1L;

      /**
        * 权限格式
        */
     public static final String ADMIN_URL_PERM = "%s:/%s%s";
}
