package com.example.demo.entity;

import com.example.demo.dto.GoodsDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="goods")
public class GoodsEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String goodsOpt;

    @Column
    private String goodsTitle;

    @Column
    private String goodsCost;

    @Column
    private String goodsBrand;

    @Column (length=500)
    private String goodsContents;

    @Column
    private int goodsHits;

    @Column
    private int fileAttached;

    @OneToMany(mappedBy = "goodsEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<GoodsFileEntity> goodsFileEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "goodsEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch=FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();

    public static GoodsEntity toSaveEntity(GoodsDTO goodsDTO) {
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setGoodsOpt(goodsDTO.getGoodsOpt());
        goodsEntity.setGoodsTitle(goodsDTO.getGoodsTitle());
        goodsEntity.setGoodsCost(goodsDTO.getGoodsCost());
        goodsEntity.setGoodsBrand(goodsDTO.getGoodsBrand());
        goodsEntity.setGoodsContents(goodsDTO.getGoodsContents());
        goodsEntity.setGoodsHits(0); // 처음 저장 시 조회수는 0
        goodsEntity.setFileAttached(goodsDTO.getFileAttached());

        return goodsEntity;
    }

    public static GoodsEntity toSaveFileEntity(GoodsDTO goodsDTO) {
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setGoodsOpt(goodsDTO.getGoodsOpt());
        goodsEntity.setGoodsTitle(goodsDTO.getGoodsTitle());
        goodsEntity.setGoodsCost(goodsDTO.getGoodsCost());
        goodsEntity.setGoodsBrand(goodsDTO.getGoodsBrand());
        goodsEntity.setGoodsContents(goodsDTO.getGoodsContents());
        goodsEntity.setGoodsHits(0);
        goodsEntity.setFileAttached(goodsDTO.getFileAttached());

        return goodsEntity;
    }
    public static GoodsEntity toUpdateEntity(GoodsDTO goodsDTO) {
        GoodsEntity goodsEntity = new GoodsEntity();
        goodsEntity.setId(goodsDTO.getId());
        goodsEntity.setGoodsOpt(goodsDTO.getGoodsOpt());
        goodsEntity.setGoodsTitle(goodsDTO.getGoodsTitle());
        goodsEntity.setGoodsCost(goodsDTO.getGoodsCost());
        goodsEntity.setGoodsBrand(goodsDTO.getGoodsBrand());
        goodsEntity.setGoodsContents(goodsDTO.getGoodsContents());
        goodsEntity.setGoodsHits(goodsDTO.getGoodsHits());
        goodsEntity.setFileAttached(goodsDTO.getFileAttached());

        return goodsEntity;
    }
}
