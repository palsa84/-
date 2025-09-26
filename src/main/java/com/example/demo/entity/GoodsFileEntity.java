package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="goods_file")
public class GoodsFileEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String originalFileName;
    @Column
    private String storedFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private GoodsEntity goodsEntity;

    public static GoodsFileEntity toGoodsFileEntity(GoodsEntity goodsEntity, String originalFileName, String storedFileName) {
        GoodsFileEntity goodsFileEntity = new GoodsFileEntity();
        goodsFileEntity.setOriginalFileName(originalFileName);
        goodsFileEntity.setStoredFileName(storedFileName);
        goodsFileEntity.setGoodsEntity(goodsEntity);
        return goodsFileEntity;
    }

}
