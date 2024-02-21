package com.xhj.message.entity;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 评论回复表
 * </p>
 *
 * @author xhj
 * @since 2023-04-17 03:06:00
 */
@Getter
@Setter
@TableName("mms_comment_reply")
public class CommentReply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论回复ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
     * 评论创建时间
     */
    @DateTimeFormat("yyyy-MM-dd hh:mm:ss")
    @TableField(value = "CREATE_TIME",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 回复的父评论ID，可为空
     */
    private Integer parentId;


}
