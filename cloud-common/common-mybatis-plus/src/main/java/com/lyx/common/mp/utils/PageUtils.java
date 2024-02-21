package com.lyx.common.mp.utils;

import lombok.Data;
import com.baomidou.mybatisplus.extension.plugins.pagination.*;

import java.io.Serializable;
import java.util.List;
/**
 * @author： xhj
 */
@Data
public class PageUtils<T> implements Serializable {
    private Long total = 0L;
    private Long pageNo;
    private Long pageSize;
    private List<T> list;

    public static PageUtils build(Page page){
        PageUtils pageUtils = new PageUtils<>();
        pageUtils.setList(page.getRecords());
        pageUtils.setPageNo(page.getCurrent());
        pageUtils.setTotal(page.getTotal());
        return pageUtils;
    }
}
