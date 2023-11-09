package com.lyx.member.mapper;

import com.lyx.member.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 黎勇炫
 * @since 2023-03-25 09:39:17
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {

    /**
     * 根据id查询用户
     * @param ids
     * @return
     */
    List<Map<String,Object>> selectUserName(List<Long> ids);

    /**
     * 获取昵称
     * @param id
     * @return
     */
    Map<String, String> getMemberName(Long id);
}
