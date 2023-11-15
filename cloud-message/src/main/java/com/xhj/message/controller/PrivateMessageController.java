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

    /**
     * 更新未读消息
     * @param
     * @return
     */
    @GetMapping("/updateMessage/{senderId}/{receiverId}")
    public R updateMessage(@PathVariable Integer senderId,
                           @PathVariable Integer receiverId){
        privateMessageService.updateMessage(senderId,receiverId);
        return R.ok();
    }


    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum+=i;
        }
        System.out.println(sum);
        
        int[] a = {21,21,12,32,45,12,32,456,778,87};
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i]<a[j]){
                    int c = a[i];
                    a[i] = a[j];
                    a[j] = c;
                }
            }
        }
        for (int i : a) {
            System.out.println(i);
        }
    }

}

