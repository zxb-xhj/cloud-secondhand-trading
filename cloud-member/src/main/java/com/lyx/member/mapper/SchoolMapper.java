package com.lyx.member.mapper;

import com.lyx.member.entity.School;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author DELL
* @description 针对表【ums_school】的数据库操作Mapper
* @createDate 2024-02-09 19:21:28
* @Entity com.lyx.member.entity.School
*/
@Mapper
public interface SchoolMapper extends BaseMapper<School> {

    List<School> getName();
}




