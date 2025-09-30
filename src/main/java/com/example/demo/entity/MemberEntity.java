package com.example.demo.entity;

import com.example.demo.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="member")
public class MemberEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userId;

    @Column
    private String userPass;

    @Column
    private String userName;


    public static MemberEntity toSaveEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserId(memberDTO.getUserId());
        memberEntity.setUserPass(memberDTO.getUserPass());
        memberEntity.setUserName(memberDTO.getUserName());
        return memberEntity;
    }
}