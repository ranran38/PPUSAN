package com.project.ppusan.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.ppusan.domain.FormData;
import com.project.ppusan.domain.Member;
import com.project.ppusan.security.UserInfo;
import com.project.ppusan.service.MemberService;



@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/member")
@Controller
public class MemberController {
    
    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입 폼
    @GetMapping(value = "/join")
    public String joinForm(Model model) {
    	model.addAttribute("FormData", new FormData());
    	return "member/join";
    }
    
    // 회원가입 완료
    @PostMapping(value = "/join")
    public String join(Member member) {
//		log.info("member : {}", formData);
    	member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        member.setMemberId(member.getMemberId() + "@" + member.getEmailAddress());
    	memberService.insertMember(member);
    	return "redirect:/";
    }
    
    // 로그인 폼
    @GetMapping(value = "/login")
    public String loginForm(Model model) {
        return "member/login";
    }

    // 로그인 성공
    @GetMapping(value = "/login-success")
    public String login_success(@AuthenticationPrincipal UserInfo userInfo, Model model) {
        log.info("userInfo : {}", userInfo);
        log.info("userInfo : {}", userInfo.getUsername());
		model.addAttribute("longinMemeber", userInfo.getMember());
        return "redirect:/";
    }

    // 로그인 실패
    @GetMapping(value = "/login-fail")
    public String login_fail() {
        log.info("login-fail");
        return "/member/login";
    }

    
}