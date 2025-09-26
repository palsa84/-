package com.example.demo.repository;

import com.example.demo.entity.NoticeEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
    List<NoticeEntity> findAllByOrderByIdDesc(); // id기준으로 최신 글

    // 🚨 누락된 findTop6ByOrderByIdDesc() 쿼리 메소드 추가
    List<NoticeEntity> findTop6ByOrderByIdDesc();


    @Modifying
    @Transactional
    @Query(value="update NoticeEntity b set b.noticeHits=b.noticeHits+1 where b.id=:id")

    void updateHits(@Param("id") Long id);
}