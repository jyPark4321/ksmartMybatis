package ksmart.mybatis.member.service;

import ksmart.mybatis.member.dto.Member;
import ksmart.mybatis.member.dto.MemberLevel;
import ksmart.mybatis.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 특정 회원 확인
     * @param memberId :회원의 아이디
     * @param memberPw :회원의 비밀번호
     * @return Map<String,Object> 결과 isCheck true: memberInfo , false
     */
    @Override
    public Map<String, Object> checkMemberInfo(String memberId, String memberPw) {

        Map<String,Object> resultMap = new HashMap<String,Object>();

        boolean isCheck = false;

        Member memberInfo = memberMapper.getMemberInfoById(memberId);
        if(memberInfo != null) {
            String checkPw = memberInfo.getMemberPw();
            if(checkPw != null && checkPw.equals(memberPw)) {
                isCheck = true;
                resultMap.put("memberInfo", memberInfo);
            }
        }

        resultMap.put("isCheck", isCheck);

        return resultMap;
    }

    /**
     * 특정회원 수정
     * @param member : 특정회원
     * @return
     */
    @Override
    public int modifyMember(Member member) {
        return memberMapper.modifyMember(member);
    }

    /**
     * 특정 회원 정보 조회
     * @param memberId
     * @return
     */
    @Override
    public Member getMemberById(String memberId) {

        return memberMapper.getMemberInfoById(memberId);
    }

    /**
     * 회원가입 프로세스
     * @param member : 회원가입 정보
     */
    @Override
    public void addMember(Member member) {
        int result = memberMapper.addMember(member);

        //if(result > 0 ) // 다음 프로세스

    }

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
