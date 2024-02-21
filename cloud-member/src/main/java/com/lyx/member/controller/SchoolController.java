package com.lyx.member.controller;

import com.lyx.common.base.result.R;
import com.lyx.member.entity.vo.SchoolVO;
import com.lyx.member.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @Author: xhj
 * @Date: 2024/02/09/19:22
 * @Description:
 */

@RestController
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @RequestMapping("/list")
    public R getSchoolList(){
        List<SchoolVO> schoolVOS = null;
        try {
            schoolVOS = schoolService.getSchoolList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok(schoolVOS);
    }
}
