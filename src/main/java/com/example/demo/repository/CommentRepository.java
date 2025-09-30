package com.example.demo.repository;

import com.example.demo.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    // 최신 댓글이 위에 오도록
    List<CommentEntity> findByGoodsEntity_IdOrderByCreatedTimeDesc(Long goodsId);
}
