package com.lyx.goods.service;

import com.lyx.goods.entity.GoodsCollect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyx.goods.entity.req.CollectReq;
import com.lyx.goods.entity.req.FootprintReq;
import com.lyx.goods.entity.vo.CollectVO;

import java.util.List;

/**
* @author DELL
* @description 针对表【gms_goods_collect】的数据库操作Service
* @createDate 2024-02-10 21:08:35
*/
public interface GoodsCollectService extends IService<GoodsCollect> {

    /**
     * 查询会员收藏列表
     * @param memberId
     * @return
     */
    List<CollectVO> getCollectList(Long memberId);

    /**
     * 添加/取消收藏列表
     * @param
     * @return
     */
    void collection(CollectReq req);
    /**
     * 查询该商品是否被收藏
     * @param
     * @return 1 是 0 否
     */
    Integer getCollection(CollectReq req);
}
