package com.lyx.member.mapper;

import com.lyx.member.entity.Area;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 省市区地区信息 Mapper 接口
 * </p>
 *
 * @author xhj
 * @since 2023-03-25 09:42:34
 */
@Mapper
public interface AreaMapper extends BaseMapper<Area> {

    List<Area> getByIdNamelist();
}
