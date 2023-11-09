package com.xhj.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xhj.message.entity.CommentReply;
import com.xhj.message.entity.req.CommentReplyReq;
import com.xhj.message.entity.vo.CommentReplyVO;
import com.xhj.message.fiegn.MemberFiegnSerivce;
import com.xhj.message.mapper.CommentReplyMapper;
import com.xhj.message.service.CommentReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 评论回复表 服务实现类
 * </p>
 *
 */
@Service

public class CommentReplyServiceImpl extends ServiceImpl<CommentReplyMapper, CommentReply> implements CommentReplyService {

    @Autowired
    private MemberFiegnSerivce memberFiegnSerivce;
    /**
     * 获取评论
     * @param id
     * @return
     */
    @Override
    public List<CommentReplyVO> getCommentReply(Integer id) {
        // 构建查询条件
        LambdaQueryWrapper<CommentReply> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(CommentReply::getGoodsId,id)
                .orderByAsc(CommentReply::getCreateTime);
        // 查询该商品评论的用户id
        List<Long> memberIds = baseMapper.selectMemberId(id);
        // 根据用户id查询用户名 远程调用
        List<Map<String, Object>> userName = memberFiegnSerivce.getMessageUserName(memberIds);
        // 查询该商品的评论
        List<CommentReply> replyList = list(wrapper);
        List<CommentReplyVO> commentReplyVOS = replyList.stream().map(replys -> {
            CommentReplyVO commentReplyVO = new CommentReplyVO();
            BeanUtils.copyProperties(replys, commentReplyVO);
            // 查询用户名
            for (Map<String, Object> map : userName) {
                Integer o = Integer.parseInt(map.get("id")+"");
                if(o.equals(commentReplyVO.getMemberId())){
                    commentReplyVO.setMemberName( map.get("nickname")!=null?map.get("nickname")+"":map.get("username")+"");
                }
                // 设置回复用户名
                if (commentReplyVO.getParentId()!=null) {
                    CommentReply reply = getById(commentReplyVO.getParentId());
                    if (o.equals(reply.getMemberId())) {
                        commentReplyVO.setParentName(map.get("nickname") != null ? map.get("nickname") + "" : map.get("username") + "");
                        commentReplyVO.setParentContent(reply.getContent());
                    }
                }
            }
            return commentReplyVO;
        }).collect(Collectors.toList());
        return commentReplyVOS;
    }

    /**
     * 发表评论
     * @param req
     * @return
     */
    @Override
    public CommentReplyVO addCommentReply(CommentReplyReq req) {
        CommentReply commentReply = new CommentReply();
        BeanUtils.copyProperties(req,commentReply);
        baseMapper.insert(commentReply);
        CommentReplyVO commentReplyVO = new CommentReplyVO();
        BeanUtils.copyProperties(commentReply,commentReplyVO);
        if (req.getParentId()!=null){
            String parentName = memberFiegnSerivce.getMemberName(Long.getLong(req.getParentId() + ""));
            commentReplyVO.setParentName(parentName);
        }
        return commentReplyVO;
    }

    /**
     * 删除评论
     * @param id
     */
    @Override
    @Transactional
    public void delete(Integer id) {
        // 删除评论
        baseMapper.deleteById(id);
        // 删除所有关于此评论的评论
        LambdaQueryWrapper<CommentReply> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(CommentReply::getParentId,id);
        baseMapper.delete(wrapper);
    }
}
