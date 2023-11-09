package com.xhj.message.entity.vo;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: xhj
 * @Date: 2023/11/09/11:21
 * @Description:
 */
@Data
public class CommentReplyVO {
    /**
     * 评论回复ID
     */
    private Integer id;

    /**
     * 商品ID
     */
    private Integer goodsId;

    /**
     * 评论的用户ID
     */
    private Integer memberId;
    /**
     * 评论的用户名
     */
    private String memberName;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论创建时间
     */
    @DateTimeFormat("yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createTime;

    /**
     * 回复的父评论ID，可为空
     */
    private Integer parentId;
    /**
     * 回复的父评论用户，可为空
     */
    private String parentName;
    /**
     * 回复的父评论，可为空
     */
    private String parentContent;
}
