package com.lyx.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.goods.entity.Goods;
import com.lyx.goods.entity.GoodsFootprint;
import com.lyx.goods.entity.req.FootprintReq;
import com.lyx.goods.entity.vo.FootprintListVO;
import com.lyx.goods.entity.vo.FootprintVO;
import com.lyx.goods.service.GoodsFootprintService;
import com.lyx.goods.mapper.GoodsFootprintMapper;
import com.lyx.goods.service.GoodsService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author DELL
* @description 针对表【gms_goods_footprint】的数据库操作Service实现
* @createDate 2024-02-10 21:09:23
*/
@Service
public class GoodsFootprintServiceImpl extends ServiceImpl<GoodsFootprintMapper, GoodsFootprint>
    implements GoodsFootprintService{

    @Autowired
    private GoodsService goodsService;

    /**
     * 添加浏览记录
     * @param req
     */
    @Override
    public void setFootprint(FootprintReq req) {
        LambdaQueryWrapper<GoodsFootprint> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(GoodsFootprint::getGoodsId,req.getGoodsId())
                .eq(GoodsFootprint::getMemberId,req.getMemberId())
                .eq(GoodsFootprint::getCreateTime,LocalDate.now());
        GoodsFootprint footprint = getOne(queryWrapper);
        GoodsFootprint goodsFootprint = new GoodsFootprint();
        if (footprint==null){
            BeanUtils.copyProperties(req,goodsFootprint);
            goodsFootprint.setCreateTime(LocalDate.now());
            save(goodsFootprint);
        }else{
            LambdaUpdateWrapper<GoodsFootprint> wrapper = Wrappers.lambdaUpdate();
            wrapper.eq(GoodsFootprint::getGoodsId,req.getGoodsId())
                    .eq(GoodsFootprint::getMemberId,req.getMemberId())
                    .eq(GoodsFootprint::getCreateTime,req.getCreateTime())
                            .set(GoodsFootprint::getCreateTime, LocalDate.now());
            update(wrapper);
        }
    }

    /**
     * 查询浏览记录
     * @param req
     * @return
     */
    @Override
    public List<Map<LocalDate,Object>> getFootprint(FootprintReq req) {
        List<Map<LocalDate,Object>> footprintVOMapS = new ArrayList<>();
        // 按时间分组查询浏览记录
        List<Map<String,Object>> footprintIds = baseMapper.getMemberGoodsId(req.getMemberId());
        // 根据商品id查询商品信息
        for (Map<String,Object> footprint : footprintIds) {
            Map<LocalDate,Object> footprintVOMap = new HashMap<>();
            System.out.println(footprint.get("goods_id"));
            System.out.println(footprint.get("create_time"));
            LambdaQueryWrapper<Goods> wrapper = Wrappers.lambdaQuery();
            wrapper.in(Goods::getId,(footprint.get("goods_id")+"").split(","));
            List<Goods> goods = goodsService.list(wrapper);
            List<FootprintVO> footprintVOS = new ArrayList<>();
            for (int i = 0; i < goods.size(); i++) {
                FootprintVO footprintVO = new FootprintVO();
                footprintVO.setGoodsId(goods.get(i).getId());
                footprintVO.setGoodsImage(goods.get(i).getDefaultImg());
                footprintVO.setGoodsName(goods.get(i).getName());
                footprintVO.setPrice(goods.get(i).getPrice());
                footprintVOS.add(footprintVO);
            }
            // 将object转成LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            footprintVOMap.put(LocalDate.parse(footprint.get("create_time")+"",formatter),footprintVOS);
            footprintVOMapS.add(footprintVOMap);
        }

        return footprintVOMapS;
    }

    /**
     * 删除浏览记录
     * @param req
     * @return
     */
    @Override
    public void delete(FootprintReq req) {
        LambdaQueryWrapper<GoodsFootprint> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(GoodsFootprint::getMemberId,req.getMemberId())
                        .eq(GoodsFootprint::getGoodsId,req.getGoodsId())
                .eq(GoodsFootprint::getCreateTime,req.getCreateTime());
        remove(wrapper);
    }
}




