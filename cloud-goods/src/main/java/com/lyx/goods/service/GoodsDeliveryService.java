package com.lyx.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lyx.goods.entity.GoodsDelivery;
import com.lyx.goods.entity.GoodsDetails;
import com.lyx.goods.entity.vo.DeliveryVO;

/**
 * @Author: xhj
 * @Date: 2024/02/09/15:44
 * @Description:
 */
public interface GoodsDeliveryService extends IService<GoodsDelivery> {
    /**
     * 根据商品id查询自提地址
     * @param id
     * @return
     */
    DeliveryVO getSchool(Long id);
}
