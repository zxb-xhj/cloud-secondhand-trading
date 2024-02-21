package com.lyx.goods.mapper;

import com.lyx.goods.entity.GoodsFootprint;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyx.goods.entity.vo.FootprintListVO;
import com.lyx.goods.entity.vo.FootprintVO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
* @author DELL
* @description 针对表【gms_goods_footprint】的数据库操作Mapper
* @createDate 2024-02-10 21:09:23
* @Entity com.lyx.goods.entity.GoodsFootprint
*/
@Mapper
public interface GoodsFootprintMapper extends BaseMapper<GoodsFootprint> {

    List<Map<String,Object>> getMemberGoodsId(Long memberId);
}




