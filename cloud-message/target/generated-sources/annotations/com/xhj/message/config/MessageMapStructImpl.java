package com.xhj.message.config;

import com.xhj.message.entity.SystemMessage;
import com.xhj.message.entity.vo.SystemMessageVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-13T01:22:22+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class MessageMapStructImpl implements MessageMapStruct {

    @Override
    public List<SystemMessageVO> systemMessageToVO(List<SystemMessage> systemMessages) {
        if ( systemMessages == null ) {
            return null;
        }

        List<SystemMessageVO> list = new ArrayList<SystemMessageVO>( systemMessages.size() );
        for ( SystemMessage systemMessage : systemMessages ) {
            list.add( systemMessageToSystemMessageVO( systemMessage ) );
        }

        return list;
    }

    protected SystemMessageVO systemMessageToSystemMessageVO(SystemMessage systemMessage) {
        if ( systemMessage == null ) {
            return null;
        }

        SystemMessageVO systemMessageVO = new SystemMessageVO();

        if ( systemMessage.getId() != null ) {
            systemMessageVO.setId( systemMessage.getId().intValue() );
        }
        systemMessageVO.setTitle( systemMessage.getTitle() );
        systemMessageVO.setContent( systemMessage.getContent() );
        systemMessageVO.setCreateTime( systemMessage.getCreateTime() );
        systemMessageVO.setUpdateTime( systemMessage.getUpdateTime() );
        systemMessageVO.setStatus( systemMessage.getStatus() );
        systemMessageVO.setDeleted( systemMessage.getDeleted() );
        systemMessageVO.setCreater( systemMessage.getCreater() );

        return systemMessageVO;
    }
}
