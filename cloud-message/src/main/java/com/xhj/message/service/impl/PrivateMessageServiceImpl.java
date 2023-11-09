package com.xhj.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xhj.message.entity.PrivateMessage;
import com.xhj.message.entity.vo.PrivateMessageListVO;
import com.xhj.message.entity.vo.PrivateMessageVO;
import com.xhj.message.fiegn.MemberFiegnSerivce;
import com.xhj.message.mapper.PrivateMessageMapper;
import com.xhj.message.service.PrivateMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xhj.message.util.WebSocketServer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>
 * 私信表 服务实现类
 * </p>
 *
 */
@Service
public class PrivateMessageServiceImpl extends ServiceImpl<PrivateMessageMapper, PrivateMessage> implements PrivateMessageService {

    @Autowired
    private MemberFiegnSerivce memberFiegnSerivce;

    @Autowired
    private WebSocketServer webSocketServer;

    @Autowired
    private ThreadPoolExecutor executor;

    /**
     * 获取消息
     * @param id
     * @return
     */
    @Override
    public PrivateMessageListVO getMessage(Integer id) {
        // 准备数据
        PrivateMessageListVO privateMessageListVO = new PrivateMessageListVO();
        LambdaQueryWrapper<PrivateMessage> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(PrivateMessage::getSenderId, id)
                .or()
                .eq(PrivateMessage::getReceiverId, id);
        // 查询用户的所有消息
        List<PrivateMessage> privateMessages = baseMapper.selectList(wrapper);
        // 构建查询条件
        List<PrivateMessageVO> privateMessageVOS = new ArrayList<>();
        Set<String> set = new HashSet<>();
        if (privateMessages.size()>0){
            for (PrivateMessage privateMessage : privateMessages) {
                PrivateMessageVO vo = new PrivateMessageVO();
                BeanUtils.copyProperties(privateMessage,vo);
                privateMessageVOS.add(vo);
                // 保存跟该用户有联系的id
                set.add(privateMessage.getSenderId()+"");
                set.add(privateMessage.getReceiverId()+"");
            }
        }
        // 保存
        privateMessageListVO.setPrivateMessageVOs(privateMessageVOS);
        // 移除自己
        set.remove(id+"");
        // 转成Long类型
        List<Long> list = new ArrayList<>();
        set.forEach(s -> {
            list.add(Long.valueOf(s));
        });
        // 远程调用获取用户名
        if (list.size()>0){
            List<Map<String,Object>> map = memberFiegnSerivce.getMessageUserName(list);
            privateMessageListVO.setList(map);
        }
        return privateMessageListVO;
    }

    /**
     * 发送消息
     * @param map
     * @return
     */
    @Override
    public PrivateMessage addMessage(Map<String, Object> map) {
        PrivateMessage privateMessage = new PrivateMessage();
        privateMessage.setContent(map.get("content")+"");
        privateMessage.setCreateTime(LocalDateTime.now());
        privateMessage.setIsRead(false);
        privateMessage.setReceiverId(Integer.parseInt(map.get("receiverId")+""));
        privateMessage.setSenderId(Integer.parseInt(map.get("senderId")+""));
        baseMapper.insertPrivateMessage(privateMessage);
        // 远程调用查询谁发送的用户名称
        List<Map<String, Object>> name = memberFiegnSerivce.getMessageUserName(Arrays.asList(Long.parseLong(map.get("senderId") + "")));
        Map<String, Object> map1 = name.get(0);
        System.out.println(map1);
        String username = map1.get("nickname")!=null?map1.get("nickname")+"":map1.get("username")+"";
        //基于WebSocket实现发送消息
        webSocketServer.sendToAllClient(map.get("receiverId")+"", map.get("senderId")+":"+username+":"+privateMessage.getContent()+"");
        return privateMessage;
    }

    public static void main(String[] args) {
        String name = "2001:xhfffffj:你好";
        System.out.println(name.substring(0,name.indexOf(":")));
        System.out.println(name.substring(name.indexOf(":")+1,name.indexOf(":",name.indexOf(":")+1)));

        System.out.println(name.substring(name.indexOf(":",name.indexOf(":")+1)+1));
//        System.out.println(name.substring(0,name.indexOf(":")));
    }
}
