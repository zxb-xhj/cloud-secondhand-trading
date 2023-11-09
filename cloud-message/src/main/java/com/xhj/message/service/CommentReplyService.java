package com.xhj.message.service;

import com.xhj.message.entity.CommentReply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xhj.message.entity.req.CommentReplyReq;
import com.xhj.message.entity.vo.CommentReplyVO;

import java.util.List;

/**
 * <p>
 * 评论回复表 服务类
 * </p>
 * xhj
 */
public interface CommentReplyService extends IService<CommentReply> {

    /**
     * 获取评论
     * @param id
     * @return
     */
    List<CommentReplyVO> getCommentReply(Integer id);

    /**
     * 发表评论
     * @param req
     * @return
     */
    CommentReplyVO addCommentReply(CommentReplyReq req);

    /**
     * 删除评论
     * @param id
     */
    void delete(Integer id);
}
