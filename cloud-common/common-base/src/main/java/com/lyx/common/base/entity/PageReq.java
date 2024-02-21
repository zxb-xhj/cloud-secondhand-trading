package com.lyx.common.base.entity;

import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
/**
 * @author： xhj
 */
@Data
public class PageReq implements Serializable {
    private Long pageNo;
    private Long pageSize;
}
