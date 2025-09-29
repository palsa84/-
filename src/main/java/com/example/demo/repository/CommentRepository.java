package com.example.demo.repository;

import com.example.demo.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    // 🌟 추가: goodsEntity의 id 값으로 댓글 목록을 조회합니다.
    // OrderByCreatedTimeDesc를 추가하여 최신 댓글이 위에 오도록 정렬합니다.
    List<CommentEntity> findByGoodsEntity_IdOrderByCreatedTimeDesc(Long goodsId);
}
