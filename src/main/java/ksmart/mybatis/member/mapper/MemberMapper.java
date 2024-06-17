package ksmart.mybatis.member.mapper;

import ksmart.mybatis.member.dto.Member;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MemberMapper {

    // 회원목록 조회
   List<Member> getMemberList();
}
