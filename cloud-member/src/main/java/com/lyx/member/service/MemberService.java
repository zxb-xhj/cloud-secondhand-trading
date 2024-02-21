package com.lyx.member.service;

import com.lyx.common.mp.utils.PageUtils;
import com.lyx.member.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyx.member.entity.req.MemberListPageReq;
import com.lyx.member.entity.req.MemberLoginReq;
import com.lyx.member.entity.req.MemberPassReq;
import com.lyx.member.entity.vo.MemberInfoVO;
import com.lyx.member.entity.vo.MemberLoginVo;
import com.lyx.member.entity.vo.MemberVO;
import com.lyx.member.entity.vo.SchoolVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xhj
 * @since 2023-03-25 09:39:17
 */
public interface MemberService extends IService<Member> {

     /**
       * 分页查询会员
       */
    PageUtils<MemberVO> pageMember(MemberListPageReq req);

     /**
       * 会员详情
       */
    MemberVO getMemberVO(Long id);

    /**
     * 会员登录
     * @param req
     * @return
     */
    MemberLoginVo login(MemberLoginReq req);

    /**
     * 查询用户是否存在
     * @param name
     * @return
     */
    String getName(String name);

    /**
     * 密码对比
     * @param req
     * @return
     */
    void getPassword(MemberPassReq req) throws Exception;

    /**
     * 修改密码
     * @param req
     */
    void updatePassword(MemberPassReq req) throws Exception;

    /**
     * 用户名是否存在
     * @param req
     * @throws Exception
     */
    void getUserByUserName(MemberPassReq req) throws Exception;

    /**
     * 用户注册
     * @param req
     */
    void register(MemberPassReq req) throws Exception;

    /**
     * 会员修改
     * @param req
     */
    void updateMember(MemberListPageReq req);

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    MemberInfoVO getMemberById(Long id);
    /**
     * 查询用户发布商品数量
     */
    Integer countMemberId(Long memberId);
    /**
     * 批量查询
     */
    List<Member> getList(List<Long> ids);

    /**
     * 获取手机号
     * @param id
     * @return
     */
    String getMemberMobile(Long id);
    /**
     * 根据id查询用户
     */
    List<Map<String,Object>> getMessageUserName(List<Long> ids);
    /**
     * 获取昵称
     * @param id
     * @return
     */
    String getMemberName(Long id);

    /**
     * 文件上传
     * @param file
     */
    void getFile(MultipartFile file) throws IOException;

    /**
     * 根据商品id查询 用户id 查询用户学校和地址
     */
    SchoolVO getSchool(Long id);
}
