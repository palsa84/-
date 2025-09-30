package com.example.demo.repository;

import com.example.demo.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByUserId(String userId);
}
