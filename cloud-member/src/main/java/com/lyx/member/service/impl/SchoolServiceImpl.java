package com.lyx.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.member.entity.Area;
import com.lyx.member.entity.School;
import com.lyx.member.entity.vo.AreaVo;
import com.lyx.member.entity.vo.SchoolVO;
import com.lyx.member.mapper.AreaMapper;
import com.lyx.member.service.AreaService;
import com.lyx.member.service.SchoolService;
import com.lyx.member.mapper.SchoolMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
* @author DELL
* @description 针对表【ums_school】的数据库操作Service实现
* @createDate 2024-02-09 19:21:28
*/
@Service
@Slf4j
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School>
    implements SchoolService{

    @Autowired
    private AreaService areaService;
    @Autowired
    private ThreadPoolExecutor executor;
    @Autowired
    private AreaMapper areaMapper;

    @Override
    @Cacheable(value = "cloud-member:school")
    public List<SchoolVO> getSchoolList() throws ExecutionException, InterruptedException {
        long millis2 = System.currentTimeMillis();
        CompletableFuture<List<School>> supplyAsync = CompletableFuture.supplyAsync(() -> {
            long millis1 = System.currentTimeMillis();
            List<School> school = baseMapper.getName();
            log.info("getName耗时为{}",System.currentTimeMillis()-millis1);
            return school;
        }, executor);
        CompletableFuture<List<Area>> supplyAsync1 = CompletableFuture.supplyAsync(() -> {
            long millis = System.currentTimeMillis();
            List<Area> list = areaMapper.getByIdNamelist();
            log.info("getByIdNamelist耗时为{}",System.currentTimeMillis()-millis);
            return list;
        }, executor);
        CompletableFuture.allOf(supplyAsync,supplyAsync1).get();
        log.info("getName getByIdNamelist总耗时为{}",System.currentTimeMillis()-millis2);
        long millis1 = System.currentTimeMillis();
        List<SchoolVO> schoolVOs = supplyAsync1.get().stream().filter((area -> area.getLevel() == 1))
                .map((area) -> {
                    SchoolVO schoolVO = new SchoolVO();
                    schoolVO.setId(area.getParentId());
                    schoolVO.setName(area.getAreaName());
                    try {
                        schoolVO.setSchoolVOS(getSchools(area,supplyAsync1.get(),supplyAsync.get()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return schoolVO;
                }).collect(Collectors.toList());
        log.info("schoolVOs耗时为{}",System.currentTimeMillis()-millis1);
        return schoolVOs;
    }

    private List<SchoolVO> getSchools(Area area, List<Area> list,List<School> school) throws ExecutionException, InterruptedException {

        CompletableFuture<List<SchoolVO>> supplyAsync1 = CompletableFuture.supplyAsync(() -> {
            List<SchoolVO> schoolVOS = list.stream().filter(areas -> areas.getParentId() == area.getId())
                    .map(item -> {
                        SchoolVO schoolVO = new SchoolVO();
                        schoolVO.setId(item.getId());
                        schoolVO.setName(item.getAreaName());
//                    schoolVO.setSchoolVOS(getSchools(item, list));
                        return schoolVO;
                    }).collect(Collectors.toList());
            return schoolVOS;
        }, executor);
        List<SchoolVO> collect = supplyAsync1.get().stream().map(schoolVO -> {
            List<SchoolVO> schoolVOList = null;
            try {
                schoolVOList = school.stream().filter(school1 -> (schoolVO.getId() + "")
                        .equals(school1.getCityId())).map(school1 -> {
                    SchoolVO schoolVO1 = new SchoolVO();
                    schoolVO1.setId(Long.parseLong(school1.getSchoolId()));
                    schoolVO1.setName(school1.getSchoolName());
                    return schoolVO1;
                }).collect(Collectors.toList());
            } catch (Exception e) {
                e.printStackTrace();
            }
            schoolVO.setSchoolVOS(schoolVOList);
            return schoolVO;
        }).collect(Collectors.toList());

        return collect;
    }
}




