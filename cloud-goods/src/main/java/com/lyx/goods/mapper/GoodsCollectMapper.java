package com.lyx.goods.mapper;

import com.lyx.goods.entity.GoodsCollect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author DELL
* @description 针对表【gms_goods_collect】的数据库操作Mapper
* @createDate 2024-02-10 21:08:35
* @Entity com.lyx.goods.entity.GoodsCollect
*/
@Mapper
public interface GoodsCollectMapper extends BaseMapper<GoodsCollect> {
    /**
     * 查询该商品是否被收藏
     * @param
     * @return 1 是 0 否
     */
    Integer getCount(Long memberId, Long goodsId);

    /**
     * 根据会员id查询收藏列表
     * @param memberId
     * @return
     */
    List<Long> getByIdlist(Long memberId);
}




