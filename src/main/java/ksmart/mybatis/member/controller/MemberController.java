package ksmart.mybatis.member.controller;

import ksmart.mybatis.member.dto.Member;
import ksmart.mybatis.member.dto.MemberLevel;
import ksmart.mybatis.member.mapper.MemberMapper;
import ksmart.mybatis.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor // 정적인 인스턴트가 잡히면 생성자 메서드에 넣어준다
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    @PostMapping("/idCheck")
    @ResponseBody
    public boolean idCheck(@RequestParam(value = "memberId") String memberId){
        log.info("아이디중복체크: memberId {}",memberId);
        boolean isMember = false;
        isMember = memberMapper.idCheck(memberId);
        return isMember;
    }

    @GetMapping("addMember")
    public String addMember(Model model) {
        List<MemberLevel> memberLevelList = memberService.getMemberLevelList();

        model.addAttribute("title", "회원가입");
        model.addAttribute("levelList", memberLevelList);

        return "admin/member/addMember";
    }

    @GetMapping("/memberList")
    public String getMemberList(Model model) {
        List<Member> memberList = memberService.getMemberList();

        log.info("회원목록조회 : {}", memberList);

        model.addAttribute("title","회원목록조회");
        model.addAttribute("memberList", memberList);

        return "admin/member/memberList";
    }
}
