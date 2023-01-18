package com.example.demo.controller;

import com.example.demo.dto.MemberLoginRequestDto;
import com.example.demo.dto.TokenInfo;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/admin")
    public TokenInfo login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {
//        System.out.println(memberLoginRequestDto.toString());
        String memberId = memberLoginRequestDto.getMemberId();
        String password = memberLoginRequestDto.getPassword();
        TokenInfo tokenInfo = memberService.login(memberId, password);
        return tokenInfo;
    }
}
