package com.lyx.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.goods.entity.GoodsCollect;
import com.lyx.goods.entity.req.CollectReq;
import com.lyx.goods.entity.req.FootprintReq;
import com.lyx.goods.entity.vo.CollectVO;
import com.lyx.goods.entity.vo.GoodsVO;
import com.lyx.goods.service.GoodsCollectService;
import com.lyx.goods.mapper.GoodsCollectMapper;
import com.lyx.goods.service.GoodsService;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author DELL
* @description 针对表【gms_goods_collect】的数据库操作Service实现
* @createDate 2024-02-10 21:08:35
*/
@Service
public class GoodsCollectServiceImpl extends ServiceImpl<GoodsCollectMapper, GoodsCollect>
    implements GoodsCollectService{

    @Autowired
    private RedissonClient redisson;
    @Autowired
    private GoodsService goodsService;

    /**
     * 查询会员收藏列表
     * @param memberId
     * @return
     */
    @Override
    public List<CollectVO> getCollectList(Long memberId) {
        List<Long> goodsIds = baseMapper.getByIdlist(memberId);
        if (goodsIds!=null&&goodsIds.size()>0){
            List<GoodsVO> goodsVO = goodsService.getByGoodsId(goodsIds);
            if (goodsVO!=null&&goodsVO.size()>0){
                List<CollectVO> collectVOS = new ArrayList<>();
                for (GoodsVO vo : goodsVO) {
                    CollectVO collectVO = new CollectVO();
                    BeanUtils.copyProperties(vo,collectVO);
                    collectVO.setGoodsId(vo.getId());
                    collectVO.setCreateTime(LocalDateTime.now());
                    collectVOS.add(collectVO);
                }
                return collectVOS;
            }
        }
        return null;
    }


    /**
     * 添加/取消收藏列表
     * @param
     * @return
     */
    @Override
    public void collection(CollectReq req) {
        RLock lock = redisson.getLock("collection");
        lock.lock();
        // 添加 1/取消 2
        if (req.getState()==1){
            GoodsCollect goodsCollect = new GoodsCollect();
            BeanUtils.copyProperties(req,goodsCollect);
            goodsCollect.setCreateTime(LocalDateTime.now());
            save(goodsCollect);
        }else {
            LambdaQueryWrapper<GoodsCollect> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(GoodsCollect::getMemberId,req.getMemberId())
                            .eq(GoodsCollect::getGoodsId,req.getGoodsId());
            remove(wrapper);
        }
        lock.unlock();
    }

    /**
     * 查询该商品是否被收藏
     * @param
     * @return 1 是 0 否
     */
    @Override
    public Integer getCollection(CollectReq req) {
        Integer count = baseMapper.getCount(req.getMemberId(),req.getGoodsId());
        return count;
    }
}




