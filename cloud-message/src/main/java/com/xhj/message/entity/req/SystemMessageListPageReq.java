package com.xhj.message.entity.req;

import com.lyx.common.mp.utils.PageUtils;
import lombok.Data;

/**
 * @author xhj
 * @date 2023年04月18日 21:51
 */
@Data
public class SystemMessageListPageReq extends PageUtils {

    private String title;

    private String creater;

}
