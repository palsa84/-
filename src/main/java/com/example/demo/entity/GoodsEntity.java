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

    // 🌟 추가: 댓글 리스트 매핑
    @OneToMany(mappedBy = "goodsEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch=FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();


    // DTO -> Entity 변환 메서드 (파일 첨부 여부는 DTO에서 가져옴)
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

    // 이 메서드는 toSaveEntity와 거의 동일하지만, 기존 코드의 구성을 유지하기 위해 포함합니다.
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
        goodsEntity.setId(goodsDTO.getId()); // ID 설정
        goodsEntity.setGoodsOpt(goodsDTO.getGoodsOpt());
        goodsEntity.setGoodsTitle(goodsDTO.getGoodsTitle());
        goodsEntity.setGoodsCost(goodsDTO.getGoodsCost());
        goodsEntity.setGoodsBrand(goodsDTO.getGoodsBrand());
        goodsEntity.setGoodsContents(goodsDTO.getGoodsContents());
        goodsEntity.setGoodsHits(goodsDTO.getGoodsHits()); // 기존 조회수 유지
        goodsEntity.setFileAttached(goodsDTO.getFileAttached());

        return goodsEntity;
    }
}
