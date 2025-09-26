package com.example.demo.repository;

import com.example.demo.entity.GoodsEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GoodsRepository extends JpaRepository<GoodsEntity, Long> {


    List<GoodsEntity> findTop6ByOrderByIdDesc();

    List<GoodsEntity> findAllByOrderByIdDesc();


    @Modifying
    @Transactional
    @Query(value = "update GoodsEntity g set g.goodsHits = g.goodsHits + 1 where g.id = :id")
    void updateHits(@Param("id") Long id);
}