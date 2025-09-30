package com.example.demo.dto;


import com.example.demo.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long id;
    private String userId;
    private String userPass;
    private String userName;
    private LocalDateTime userCreatedTime;


    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setUserId(memberEntity.getUserId());
        memberDTO.setUserPass(memberEntity.getUserPass());
        memberDTO.setUserName(memberEntity.getUserName());
        memberDTO.setUserCreatedTime(memberEntity.getCreatedTime());

        return memberDTO;
    }
}