package com.xhj.message.controller;

import com.lyx.common.base.result.R;
import com.xhj.message.entity.req.CommentReplyReq;
import com.xhj.message.entity.vo.CommentReplyVO;
import com.xhj.message.entity.vo.PrivateMessageListVO;
import com.xhj.message.service.CommentReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * 评论回复表 前端控制器
 * </p>
 *
 * @author xhj
 * @date 2023年10月26日 15:01
 */
@RestController
@RequestMapping("/commentReply")
public class CommentReplyController {

    @Autowired
    private CommentReplyService commentReplyService;

    /**
     * 获取评论
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R getCommentReply(@PathVariable("id") Integer id){
        List<CommentReplyVO> commentReplyVOs = null;
        try {
            commentReplyVOs = commentReplyService.getCommentReply(id);
        } catch (Exception e) {
            e.printStackTrace();
            R.failed();
        }
        return R.ok(commentReplyVOs);
    }
    /**
     * 删除评论
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") Integer id){
        commentReplyService.delete(id);
        return R.ok();
    }

    /**
     * 添加评论
     * @param req
     * @return
     */
    @PostMapping("/save")
    public R addCommentReply(@RequestBody CommentReplyReq req){
        CommentReplyVO commentReplyVOs = commentReplyService.addCommentReply(req);
        return R.ok(commentReplyVOs);
    }
}

