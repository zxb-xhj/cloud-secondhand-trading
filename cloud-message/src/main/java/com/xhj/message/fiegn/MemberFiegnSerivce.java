package com.xhj.message.fiegn;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @Author: xhj
 * @Date: 2023/10/27/14:18
 * @Description:
 */
@FeignClient("cloud-member")
public interface MemberFiegnSerivce {

    @PostMapping("/member/getMessageUserName")
    List<Map<String,Object>> getMessageUserName(@RequestBody List<Long> ids);

    /**
     * 获取昵称
     * @param id
     * @return
     */
    @GetMapping("/member/memberName/{id}")
    String getMemberName(@PathVariable Long id);
}
