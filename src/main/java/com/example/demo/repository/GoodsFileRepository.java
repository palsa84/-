package com.example.demo.repository;

import com.example.demo.entity.GoodsFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsFileRepository extends JpaRepository<GoodsFileEntity, Long> {
}
