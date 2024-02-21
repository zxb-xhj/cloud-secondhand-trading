package com.lyx.member.config;

import com.lyx.member.entity.Member;
import com.lyx.member.entity.MemberAddr;
import com.lyx.member.entity.vo.MemberAddrVO;
import com.lyx.member.entity.vo.MemberVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-20T19:52:05+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class MemberMapStructImpl implements MemberMapStruct {

    @Override
    public List<MemberVO> memberToMemberVO(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<MemberVO> list = new ArrayList<MemberVO>( members.size() );
        for ( Member member : members ) {
            list.add( memberToMemberVO1( member ) );
        }

        return list;
    }

    @Override
    public List<MemberAddrVO> memberAddrToMemberAddrVO(List<MemberAddr> records) {
        if ( records == null ) {
            return null;
        }

        List<MemberAddrVO> list = new ArrayList<MemberAddrVO>( records.size() );
        for ( MemberAddr memberAddr : records ) {
            list.add( memberAddrToMemberAddrVO1( memberAddr ) );
        }

        return list;
    }

    protected MemberVO memberToMemberVO1(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberVO memberVO = new MemberVO();

        memberVO.setId( member.getId() );
        memberVO.setUsername( member.getUsername() );
        memberVO.setNickname( member.getNickname() );
        memberVO.setMobile( member.getMobile() );
        memberVO.setEmail( member.getEmail() );
        memberVO.setAvatar( member.getAvatar() );
        memberVO.setGender( member.getGender() );
        memberVO.setSchoolName( member.getSchoolName() );
        memberVO.setStatus( member.getStatus() );
        memberVO.setLastLoginTime( member.getLastLoginTime() );
        memberVO.setCreateTime( member.getCreateTime() );
        memberVO.setUpdateTime( member.getUpdateTime() );

        return memberVO;
    }

    protected MemberAddrVO memberAddrToMemberAddrVO1(MemberAddr memberAddr) {
        if ( memberAddr == null ) {
            return null;
        }

        MemberAddrVO memberAddrVO = new MemberAddrVO();

        memberAddrVO.setAddrId( memberAddr.getAddrId() );
        memberAddrVO.setMemberId( memberAddr.getMemberId() );
        memberAddrVO.setMobile( memberAddr.getMobile() );
        memberAddrVO.setIsDefault( memberAddr.getIsDefault() );
        memberAddrVO.setConsignee( memberAddr.getConsignee() );
        memberAddrVO.setProvinceId( memberAddr.getProvinceId() );
        memberAddrVO.setProvince( memberAddr.getProvince() );
        memberAddrVO.setCityId( memberAddr.getCityId() );
        memberAddrVO.setCity( memberAddr.getCity() );
        memberAddrVO.setAreaId( memberAddr.getAreaId() );
        memberAddrVO.setArea( memberAddr.getArea() );
        memberAddrVO.setPostCode( memberAddr.getPostCode() );
        memberAddrVO.setAddr( memberAddr.getAddr() );
        memberAddrVO.setLng( memberAddr.getLng() );
        memberAddrVO.setLat( memberAddr.getLat() );

        return memberAddrVO;
    }
}
