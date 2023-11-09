package com.xhj.message.controller;

import com.lyx.common.base.result.R;
import com.xhj.message.entity.PrivateMessage;
import com.xhj.message.entity.vo.PrivateMessageListVO;
import com.xhj.message.service.PrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * 私信表 前端控制器
 * </p>
 *
 * @author xhj
 * @date 2023年10月26日 15:01
 */
@RestController
@RequestMapping("/privateMessage")
public class PrivateMessageController {

    @Autowired
    private PrivateMessageService privateMessageService;

    /**
     * 获取消息
     * @param id
     * @return
     */
    @GetMapping("/getMessage/{id}")
    public R getMessage(@PathVariable("id") Integer id){
        PrivateMessageListVO privateMessageListVO = privateMessageService.getMessage(id);
        System.out.println(privateMessageListVO);
        return R.ok(privateMessageListVO);
    }

    /**
     * 发送消息
     * @param map
     * @return
     */
    @PostMapping("/webSocket")
    public R webSocket(@RequestBody Map<String,Object> map){
        PrivateMessage privateMessage = privateMessageService.addMessage(map);
        return R.ok(privateMessage);
    }

}

