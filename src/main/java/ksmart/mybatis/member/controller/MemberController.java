package ksmart.mybatis.member.controller;

import ksmart.mybatis.member.dto.Member;
import ksmart.mybatis.member.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor // 정적인 인스턴트가 잡히면 생성자 메서드에 넣어준다
@Slf4j
public class MemberController {

    private final MemberMapper memberMapper;

    @GetMapping("/memberList")
    public String getMemberList(Model model) {
        List<Member> memberList = memberMapper.getMemberList();

        log.info("회원목록조회 : {}", memberList);
        return "admin/member/memberList";
    }
}
