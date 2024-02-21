package com.lyx.goods.controller;

import com.lyx.common.base.result.R;
import com.lyx.goods.entity.req.FootprintReq;
import com.lyx.goods.entity.vo.FootprintVO;
import com.lyx.goods.service.GoodsFootprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @Author: xhj
 * @Date: 2024/02/10/21:10
 * @Description:
 */
@RestController
@RequestMapping("/footprint")
public class GoodsFootprintController {

    @Autowired
    private GoodsFootprintService footprintService;

    /**
     * 添加浏览记录
     * @param
     * @return
     */
    @GetMapping("/addFootprint")
    public R setFootprint(FootprintReq req){
        footprintService.setFootprint(req);
        return R.ok();
    }

    /**
     * 查询浏览记录
     * @param req
     * @return
     */
    @GetMapping ("/footprintList")
    public R getFootprint(FootprintReq req){
        List<Map<LocalDate,Object>> footprintVO = footprintService.getFootprint(req);
        return R.ok(footprintVO);
    }

    /**
     * 删除浏览记录
     * @param req
     * @return
     */
    @DeleteMapping ("/delete")
    public R dalete(FootprintReq req){
        footprintService.delete(req);
        return R.ok();
    }
}
