package com.example.demo.controller;

import com.example.demo.dto.MemberDTO;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/join")
    public String saveForm() {
        return "/member/join";
    }

    @PostMapping("/idcheck")
    public @ResponseBody String idcheck(@RequestParam("userId") String userId) {
        String checkResult = memberService.idCheck(userId);
        return checkResult;
    }

    @PostMapping("/save")
    @ResponseBody
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "<script>alert('회원가입을 축하합니다'); location.href='/'; </script>";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/member/login";
    }

    @GetMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    @ResponseBody
    public String loginok(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult != null ) {
            // 로그인 성공
            session.setAttribute("userId", loginResult.getUserId());
            session.setAttribute("userName", loginResult.getUserName());
            session.setMaxInactiveInterval(60*30);
            return "<script>location.href='/' </script>";
        } // 로그인 실패
        else {
            return "<script>alert('아이디 또는 비밀번호가 올바르지 않습니다.'); location.href='/member/login';</script>";
        }
    }
    @GetMapping("/mypage")
    public String mypage() {
        return "/member/mypage";
    }

}

