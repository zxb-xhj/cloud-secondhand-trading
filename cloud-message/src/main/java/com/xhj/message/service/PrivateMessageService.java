package com.xhj.message.service;

import com.xhj.message.entity.PrivateMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhj.message.entity.vo.PrivateMessageListVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 私信表 服务类
 * </p>
 *
 */
public interface PrivateMessageService extends IService<PrivateMessage> {
    /**
     * 获取消息
     * @param id
     * @return
     */
    PrivateMessageListVO getMessage(Integer id);

    /**
     * 发送消息
     * @param map
     * @return
     */
    PrivateMessage addMessage(Map<String, Object> map);

    /**
     * 更新未读消息
     * @param senderId
     * @param receiverId
     */
    void updateMessage(Integer senderId, Integer receiverId);
}
