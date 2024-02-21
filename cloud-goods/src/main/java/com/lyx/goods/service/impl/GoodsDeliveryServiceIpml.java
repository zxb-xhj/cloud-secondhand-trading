package com.lyx.goods.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.goods.entity.GoodsDelivery;
import com.lyx.goods.entity.GoodsDetails;
import com.lyx.goods.entity.vo.DeliveryVO;
import com.lyx.goods.mapper.GoodsDeliveryMapper;
import com.lyx.goods.mapper.GoodsDetailsMapper;
import com.lyx.goods.service.GoodsDeliveryService;
import com.lyx.goods.service.GoodsDetailsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @Author: xhj
 * @Date: 2024/02/09/15:43
 * @Description:
 */
@Service
public class GoodsDeliveryServiceIpml  extends ServiceImpl<GoodsDeliveryMapper, GoodsDelivery> implements GoodsDeliveryService {

    /**
     * 根据商品id查询自提地址
     * @param id
     * @return
     */
    @Override
    public DeliveryVO getSchool(Long id) {
        GoodsDelivery delivery = baseMapper.getSchool(id);
        DeliveryVO deliveryVO = new DeliveryVO();
        BeanUtils.copyProperties(delivery,deliveryVO);
        return deliveryVO;
    }
}

