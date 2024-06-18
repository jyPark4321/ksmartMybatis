package ksmart.mybatis.member.mapper;

import ksmart.mybatis.member.dto.Member;
import ksmart.mybatis.member.dto.MemberLevel;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MemberMapper {

   // 회원 수정
   int modifyMember(Member member);

   // 특정회원정보조회
   Member getMemberInfoById(String memberId);
   //회원가입
   int addMember(Member member);

   // 아이디 중복체크
   boolean idCheck(String memberId);
   // 회원등급 조회
   List<MemberLevel> getMemberLevelList();
   // 회원목록 조회
   List<Member> getMemberList();
}
