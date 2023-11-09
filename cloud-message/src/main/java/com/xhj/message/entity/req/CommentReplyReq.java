package com.xhj.message.entity.req;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: xhj
 * @Date: 2023/11/09/12:17
 * @Description:
 */
@Data
public class CommentReplyReq {
    /**
     * 评论的用户ID
     */
    private Integer memberId;

    /**
     * 商品ID
     */
    private Integer goodsId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 回复的父评论ID，可为空
     */
    private Integer parentId;
}
