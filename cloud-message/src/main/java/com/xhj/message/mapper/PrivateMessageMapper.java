package com.xhj.message.mapper;

import com.xhj.message.entity.PrivateMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 私信表 Mapper 接口
 * </p>
 *
 * @author xhj
 * @since 2023-04-17 03:06:00
 */
@Mapper
public interface PrivateMessageMapper extends BaseMapper<PrivateMessage> {

    /**
     * 保存消息
     * @param privateMessage
     * @return
     */
    void insertPrivateMessage(PrivateMessage privateMessage);
}
