package com.lyx.goods.service;

import com.lyx.goods.entity.GoodsFootprint;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyx.goods.entity.req.FootprintReq;
import com.lyx.goods.entity.vo.FootprintVO;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
* @author DELL
* @description 针对表【gms_goods_footprint】的数据库操作Service
* @createDate 2024-02-10 21:09:23
*/
public interface GoodsFootprintService extends IService<GoodsFootprint> {

    void setFootprint(FootprintReq req);
    /**
     * 查询浏览记录
     * @param req
     * @return
     */
    List<Map<LocalDate,Object>> getFootprint(FootprintReq req);

    /**
     * 删除浏览记录
     * @param req
     * @return
     */
    void delete(FootprintReq req);
}
