package com.lyx.goods.controller;

import com.lyx.common.base.result.R;
import com.lyx.goods.entity.vo.DeliveryVO;
import com.lyx.goods.service.GoodsDeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: xhj
 * @Date: 2024/02/14/22:52
 * @Description:
 */
@RestController
@RequestMapping("/delivery")
public class GoodsDeliveryController {

    @Autowired
    GoodsDeliveryService goodsDeliveryService;

    /**
     * 根据商品id查询自提地址
     * @param id
     * @return
     */
    @GetMapping ("/getSchool/{id}")
    public R getSchool(@PathVariable("id") Long id){
        DeliveryVO deliveryVO = goodsDeliveryService.getSchool(id);
        return R.ok(deliveryVO);
    }
}
