package com.lyx.member.service.impl;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lyx.common.mp.utils.PageUtils;
import com.lyx.common.base.exception.BizException;
import com.lyx.common.base.result.ResultCode;
import com.lyx.member.config.MemberMapStruct;
import com.lyx.member.entity.Member;
import com.lyx.member.entity.MemberAddr;
import com.lyx.member.entity.req.MemberListPageReq;
import com.lyx.member.entity.req.MemberLoginReq;
import com.lyx.member.entity.req.MemberPassReq;
import com.lyx.member.entity.vo.MemberInfoVO;
import com.lyx.member.entity.vo.MemberLoginVo;
import com.lyx.member.entity.vo.MemberVO;
import com.lyx.member.entity.vo.SchoolVO;
import com.lyx.member.mapper.MemberMapper;
import com.lyx.member.service.MemberAddrService;
import com.lyx.member.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyx.member.utils.JwtUtil;
import com.lyx.member.utils.MobileEncrypt;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.member;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xhj
 * @since 2023-03-25 09:39:17
 */
@Service
@Slf4j
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {


    @Autowired
    private MemberMapStruct memberMapStruct;
    @Autowired
    private MemberAddrService memberAddrService;

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedissonClient redissonClient;

    @Value("${com.name}")
    private String name;
    @Override
    public PageUtils<MemberVO> pageMember(MemberListPageReq req) {
        // 构建分页对象 设置分页参数
        Page<Member> page = new Page<>(req.getPageNo(),req.getPageSize());
        // 构建查询条件
        LambdaQueryWrapper<Member> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StringUtils.isNotEmpty(req.getUsername()),Member::getUsername,req.getUsername())
                .like(StringUtils.isNotEmpty(req.getNickname()),Member::getNickname,req.getNickname())
                .like(StringUtils.isNotEmpty(req.getMobile()),Member::getMobile,req.getMobile())
                .like(StringUtils.isNotEmpty(req.getEmail()),Member::getEmail,req.getEmail())
                .orderByAsc(Member::getId);
        baseMapper.selectPage(page,wrapper);
        PageUtils<MemberVO> pageUtils = new PageUtils<>();
        // 手机号加密
//        page.getRecords().forEach(item->{
//            item.setMobile(MobileEncrypt.encrypt(item.getMobile()));
//        });
        // 转换vo
        pageUtils.setList(memberMapStruct.memberToMemberVO(page.getRecords()));
        pageUtils.setPageNo(page.getCurrent());
        pageUtils.setTotal(page.getTotal());
        // 返回分页对象
        return pageUtils;
    }

    /**
     * 会员详情
     *
     * @param id
     */
    @Override
    public MemberVO getMemberVO(Long id) {
        MemberVO vo = new MemberVO();
        // 查询会员
        Member byId = getById(id);
        if(null == byId){
            throw new BizException(ResultCode.USER_NOT_EXIST);
        }
        BeanUtils.copyProperties(byId,vo);
        // 手机号码加密
        if(vo.getMobile()!=null){
            vo.setMobile(MobileEncrypt.encrypt(vo.getMobile()));
        }
        // 设置默认收获地址
        LambdaQueryWrapper<MemberAddr> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(MemberAddr::getMemberId,id).eq(MemberAddr::getIsDefault,1);
        vo.setMemberAddr(memberAddrService.getOne(wrapper));
        return vo;
    }

    /**
     * 会员登录
     * @param req
     * @return
     */
    @Override
    public MemberLoginVo login(MemberLoginReq req) {
        LambdaQueryWrapper<Member> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StringUtils.isNotEmpty(req.getUsername()),Member::getUsername,req.getUsername());
        Member one = getOne(wrapper);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(req.getPassword(),one.getPassword());
        MemberLoginVo memberLoginVo = new MemberLoginVo();
        if (matches){
            Member member = new Member();
            member.setUsername(one.getUsername());
            member.setNickname(one.getNickname());
            member.setId(one.getId());
            memberLoginVo.setMember(member);
            memberLoginVo.setId(one.getId().intValue());
            System.out.println(name);
            String token = JwtUtil.generateToken(req.getUsername(), name);
            memberLoginVo.setToken(token);
            return memberLoginVo;
        }
        return null;
    }

    /**
     * 查询用户是否存在
     * @param name
     * @return
     */
    @Override
    public String getName(String name) {
        LambdaQueryWrapper<Member> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Member::getUsername,name);
        Member member = this.getOne(wrapper);
        if (member!=null){
            return member.getUsername();
        }
        return null;
    }

    /**
     * 密码对比
     * @param req
     * @return
     */
    @Override
    public void getPassword(MemberPassReq req) throws Exception {
        LambdaQueryWrapper<Member> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(StringUtils.isNotEmpty(req.getUid()+""),Member::getId,req.getUid());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Member one = getOne(wrapper);
        boolean matches = passwordEncoder.matches(req.getPassword(), one.getPassword());
        if (!matches){
            throw new Exception();
        }
    }

    /**
     * 修改密码
     * @param req
     */
    @Override
    public void updatePassword(MemberPassReq req) throws Exception {
            Member member = getById(req.getUid());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (member!=null&&req.getPassword()!=null){
                LambdaUpdateWrapper<Member> updateWrapper = Wrappers.lambdaUpdate();
                updateWrapper.eq(Member::getId,req.getUid())
                                .set(Member::getPassword,passwordEncoder.encode(req.getPassword()));
                update(updateWrapper);
            }else{
                throw new Exception();
            }

    }

    /**
     * 用户名是否存在
     * @param req
     * @throws Exception
     */
    @Override
    public void getUserByUserName(MemberPassReq req) throws Exception {
        LambdaQueryWrapper<Member> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Member::getUsername,req.getName());
        int count = count(wrapper);
        if (count>0){
            throw new Exception();
        }
    }

    /**
     * 用户注册
     * @param req
     */
    @Override
    public void register(MemberPassReq req) throws Exception {
        if (req.getName()==null||req.getPassword()==null||req.getSchoolName()==null){
            throw new Exception();
        }
        Member member = new Member();
        member.setUsername(req.getName());
        member.setSchoolName(req.getSchoolName());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(req.getPassword());
        member.setPassword(encode);
        save(member);
    }

    /**
     * 会员修改
     * @param req
     */
    @Override
    @CacheEvict(value = "cloud-member",key = "#req.memberId")
    public void updateMember(MemberListPageReq req) {
        LambdaUpdateWrapper<Member> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(req.getMemberId()!=null,Member::getId,req.getMemberId())
                .set(req.getNickname()!=null,Member::getNickname,req.getNickname())
                .set(req.getMobile()!=null,Member::getMobile,req.getMobile())
                .set(req.getEmail()!=null,Member::getEmail,req.getEmail())
                .set(req.getSchoolName()!=null,Member::getSchoolName,req.getSchoolName());
        update(wrapper);
//        redisTemplate.delete("cloud-member:member"+req.getMemberId());
    }

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @Override
//    @Cacheable(value = "cloud-member",key = "#id")
    public MemberInfoVO getMemberById(Long id) {
        Member member = baseMapper.getByIdMember(id);
        MemberInfoVO memberInfoVO = new MemberInfoVO();
        BeanUtils.copyProperties(member,memberInfoVO);
        return memberInfoVO;
    }

    @Override
    public Integer countMemberId(Long memberId) {
        LambdaQueryWrapper<Member> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(Member::getId,memberId);
        int count = count(wrapper);
        return count;
    }
    /**
     * 批量查询
     */
    @Override
    public List<Member> getList(List<Long> ids) {
        List<Member> members = baseMapper.selectBatchIds(ids);
        return members;
    }

    /**
     * 获取手机号
     * @param id
     * @return
     */
    @Override
    public String getMemberMobile(Long id) {
        Member member = getById(id);
        return member.getMobile();
    }

    /**
     * 根据id查询用户
     */
    @Override
    public List<Map<String,Object>> getMessageUserName(List<Long> ids) {
        if (ids.size()>0){
            return baseMapper.selectUserName(ids);
        }
        return null;
    }

    /**
     * 获取昵称
     * @param id
     * @return
     */
    @Override
    public String getMemberName(Long id) {
        Map<String,String> name = baseMapper.getMemberName(id);
        return name.get("nickname")!=null?name.get("nickname"):name.get("username");
    }

    /**
     * 文件上传
     * @param file
     */
    @Override
    public void getFile(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        FileOutputStream stream = new FileOutputStream("D:/"+file.getOriginalFilename());
        int offset = 0;
        int length = 1024; // 每次写入的字节数
        while (offset < bytes.length) {
            int remaining = Math.min(length, bytes.length - offset);
            stream.write(bytes, offset, remaining);
            offset += remaining;
        }
        stream.close();
    }

    /**
     * 根据商品id查询 用户id 查询用户学校和地址
     */
    @Override
    public SchoolVO getSchool(Long id) {
        // 查询该商品是谁发布

        // 根据会员id查询学校和详细地址


        return null;
    }
}
