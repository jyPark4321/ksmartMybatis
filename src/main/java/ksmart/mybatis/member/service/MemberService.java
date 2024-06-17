package ksmart.mybatis.member.service;

import ksmart.mybatis.member.dto.Member;
import ksmart.mybatis.member.dto.MemberLevel;

import java.util.List;

public interface MemberService {

    //서비스에 대한 추상메소드 선언
    //회원 등급 조회
    List<MemberLevel> getMemberLevelList();
    //회원 목록 조회
    List<Member> getMemberList();

}
