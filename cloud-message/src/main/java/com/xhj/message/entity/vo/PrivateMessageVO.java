package com.xhj.message.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: xhj
 * @Date: 2023/10/27/11:38
 * @Description:
 */
@Data
public class PrivateMessageVO {
    private Integer id;

    /**
     * 发送方的用户ID
     */
    private Integer senderId;

    /**
     * 接收方的用户ID
     */
    private Integer receiverId;
    /**
     * 发送方的用户
    private String sender;

    /**
     * 接收方的用户
     */
//    private String receiver;

    /**
     * 私信内容
     */
    private String content;

    /**
     * 私信创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否已读，0未读，1已读
     */
    private Boolean isRead;

}
