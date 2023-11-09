package com.xhj.message.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author: xhj
 * @Date: 2023/10/27/11:46
 * @Description:
 */
@Data
public class PrivateMessageListVO implements Serializable {
    /**
     * 消息
     */
    private List<PrivateMessageVO> privateMessageVOs;

    /**
     * 消息列表
     */
    private List<Map<String,Object>> list;
}
