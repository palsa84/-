// GoodsRepository.java

package com.example.demo.repository;

import com.example.demo.entity.GoodsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page; // import 추가
import org.springframework.data.domain.Pageable; // import 추가
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoodsRepository extends JpaRepository<GoodsEntity, Long> {

    List<GoodsEntity> findTop6ByOrderByIdDesc();

    List<GoodsEntity> findAllByOrderByIdDesc();

    // 🌟 이 메서드가 반드시 있어야 합니다.
    Page<GoodsEntity> findByGoodsOpt(String goodsOpt, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update GoodsEntity g set g.goodsHits = g.goodsHits + 1 where g.id = :id")
    void updateHits(@Param("id") Long id);
}