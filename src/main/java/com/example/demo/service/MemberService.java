package com.example.demo.service;


import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        String checkResult = idCheck(memberDTO.getUserId());
        if("ok".equals(checkResult)) {
            MemberEntity memberEntity = MemberEntity.toSaveEntity(memberDTO);
            memberRepository.save(memberEntity);
        }
    }

    public String idCheck(String userId) {
        Optional<MemberEntity> byUserId =
                memberRepository.findByUserId(userId);
        if (byUserId.isPresent()) {
            return null;
        } else {
            return "ok";
        }
    }


    // MemberService.java
    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byUserId = memberRepository.findByUserId(memberDTO.getUserId());

        if (byUserId.isPresent()) {
            // 1. 조회결과가 있다면 (아이디 존재)
            MemberEntity memberEntity = byUserId.get();
            if (memberEntity.getUserPass().equals(memberDTO.getUserPass())) {
                // 2. 비밀번호가 일치하다면 -> 로그인 성공
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                // 3. 비밀번호가 일치하지 않다면 -> 로그인 실패
                return null;
            }
        } else {
            // 4. 아이디가 존재하지 않다면 -> 로그인 실패
            return null;
        }
    }

}
