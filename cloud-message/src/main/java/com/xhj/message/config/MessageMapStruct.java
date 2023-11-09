package com.xhj.message.config;

import com.xhj.message.entity.SystemMessage;
import com.xhj.message.entity.vo.SystemMessageVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapStruct {

    List<SystemMessageVO> systemMessageToVO(List<SystemMessage> systemMessages);

}
