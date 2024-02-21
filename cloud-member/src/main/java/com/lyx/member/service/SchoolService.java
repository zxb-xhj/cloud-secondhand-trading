package com.lyx.member.service;

import com.lyx.member.entity.School;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyx.member.entity.vo.SchoolVO;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
* @author DELL
* @description 针对表【ums_school】的数据库操作Service
* @createDate 2024-02-09 19:21:28
*/
public interface SchoolService extends IService<School> {

    List<SchoolVO> getSchoolList() throws ExecutionException, InterruptedException;
}
