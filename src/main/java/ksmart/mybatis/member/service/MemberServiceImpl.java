package ksmart.mybatis.member.service;

import ksmart.mybatis.member.dto.Member;
import ksmart.mybatis.member.dto.MemberLevel;
import ksmart.mybatis.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Transactional : 트랜잭션 처리 (ACID)
 * 클래스위에 @Transactional 선언하게 되면 클래스 내부의 모든 메소드에 적용
 * 메소드위에 @Transactional 선언하게 되면 특정메소드에 적용
 */
@Service("memberService")
@Transactional //jdbc라이브러리 추가했을 때 트랜잭션에 해당하는 어노테이션
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberMapper memberMapper;


    /**
     * 회원등급 조회
     * @author : ksmart51기 박중연 (2024-06-17)
     * @detail : 특이사항에 대한 내용 기술
     * @return List<MemberLevel>
     */
    @Override
    public List<MemberLevel> getMemberLevelList() {
        return memberMapper.getMemberLevelList();
    }

    @Override
    public List<Member> getMemberList() {
        List<Member> memberList = memberMapper.getMemberList();

        log.info("memberServive memberList: {}",memberList);

        if(memberList != null){
            memberList.forEach(member -> {
                int memberLevel = member.getMemberLevel();
                switch (memberLevel) {
                    case 1:
                        member.setMemberLevelName("관리자");
                        break;
                    case 2:
                        member.setMemberLevelName("판매자");
                        break;
                    case 3:
                        member.setMemberLevelName("구매자");
                        break;
                    default:
                        member.setMemberLevelName("일반회원");
                        break;
                }
            });
        }
        return memberList;
    }
}
