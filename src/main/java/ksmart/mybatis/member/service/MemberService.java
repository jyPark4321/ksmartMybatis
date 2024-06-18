package ksmart.mybatis.member.service;

import ksmart.mybatis.member.dto.Member;
import ksmart.mybatis.member.dto.MemberLevel;

import java.util.List;
import java.util.Map;

public interface MemberService {

    //서비스에 대한 추상메소드 선언
    // 회원 정보 확인
    Map<String, Object> checkMemberInfo(String memberId,String memberPw);
    //회원 정보 수정
    int modifyMember(Member member);
    // 특정 회원 정보 조회
    Member getMemberById(String memberId);
    // 회원가입
    void addMember(Member member);
    //회원 등급 조회
    List<MemberLevel> getMemberLevelList();
    //회원 목록 조회
    List<Member> getMemberList();

}
