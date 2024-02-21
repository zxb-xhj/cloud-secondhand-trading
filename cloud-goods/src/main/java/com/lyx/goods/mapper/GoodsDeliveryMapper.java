package com.lyx.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyx.goods.entity.GoodsDelivery;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: xhj
 * @Date: 2024/02/09/15:44
 * @Description:
 */
@Mapper
public interface GoodsDeliveryMapper extends BaseMapper<GoodsDelivery> {

    /**
     * 根据商品id查询自提地址
     * @param id
     * @return
     */
    GoodsDelivery getSchool(Long id);
}