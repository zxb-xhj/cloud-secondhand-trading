package com.lyx.member.entity.req;

import com.lyx.common.base.entity.PageReq;
import lombok.Data;

/**
 * @author xhj
 * @date 2023年03月26日 12:00
 */
@Data
public class MemberListPageReq extends PageReq {

    private String username;
    private String nickname;
    private String mobile;
    private String email;
    private String schoolName;
    private Long memberId;

}
