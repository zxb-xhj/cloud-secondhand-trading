package com.xhj.message.service;

import com.lyx.common.mp.utils.PageUtils;
import com.xhj.message.entity.SystemMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhj.message.entity.req.SystemMessageListPageReq;
import com.xhj.message.entity.vo.SystemMessageVO;

/**
 * <p>
 * 系统消息表 服务类
 * </p>
 *
 * @author xhj
 * @since 2023-04-17 03:06:01
 */
public interface SystemMessageService extends IService<SystemMessage> {

     /**
       * 分页查询系统消息
       */
    PageUtils<SystemMessageVO> listPage(SystemMessageListPageReq req);
}
