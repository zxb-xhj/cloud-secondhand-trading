package com.lyx.goods.controller;

import com.lyx.common.base.result.R;
import com.lyx.goods.entity.req.CollectReq;
import com.lyx.goods.entity.req.FootprintReq;
import com.lyx.goods.entity.vo.CollectVO;
import com.lyx.goods.service.GoodsCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: xhj
 * @Date: 2024/02/10/21:11
 * @Description:
 */
@RestController
@RequestMapping("/collect")
public class GoodsCollectController {

    @Autowired
    private GoodsCollectService goodsCollectService;

    /**
     * 查询会员收藏列表
     * @param memberId
     * @return
     */
    @GetMapping("/list")
    public List<CollectVO> list(Long memberId){
        return goodsCollectService.getCollectList(memberId);
    }

    /**
     * 查询该商品是否被收藏
     * @param
     * @return 1 是 0 否
     */
    @GetMapping("/getCollection")
    public Integer getCollection(CollectReq req){
        return goodsCollectService.getCollection(req);
    }

    /**
     * 添加取消收藏
     * @param req
     * @return
     */
    @GetMapping("/collection")
    public R collection(CollectReq req){
        goodsCollectService.collection(req);
        return R.ok();
    }

}
