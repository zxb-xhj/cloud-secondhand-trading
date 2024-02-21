package com.xhj.message.mapper;

import com.xhj.message.entity.CommentReply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 评论回复表 Mapper 接口
 * </p>
 *
 * @author xhj
 * @since 2023-04-17 03:06:00
 */
@Mapper
public interface CommentReplyMapper extends BaseMapper<CommentReply> {

    /**
     * 查询评论该商品的所有用户id
     * @param id
     * @return
     */
    List<Long> selectMemberId(Integer id);
}
