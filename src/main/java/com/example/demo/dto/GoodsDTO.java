package com.example.demo.dto;

import com.example.demo.entity.GoodsEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDTO {
    private Long id;
    private String goodsOpt;
    private String goodsTitle;
    private String goodsCost;
    private String goodsBrand;
    private String goodsContents;
    private int goodsHits;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    private int goodsNo;

    private MultipartFile goodsFile; //파일 담는 용도
    private String originalFileName; //원본파일이름
    private String storedFileName; //저장용 파일이름
    private int fileAttached; //파일 첨부 여부


    private String category;

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }


    public static GoodsDTO toGoodsDTO(GoodsEntity goodsEntity) {
        GoodsDTO goodsDTO = new GoodsDTO();

        goodsDTO.setId(goodsEntity.getId());
        goodsDTO.setGoodsOpt(goodsEntity.getGoodsOpt());
        goodsDTO.setGoodsTitle(goodsEntity.getGoodsTitle());
        goodsDTO.setGoodsCost(goodsEntity.getGoodsCost());
        goodsDTO.setGoodsBrand(goodsEntity.getGoodsBrand());
        goodsDTO.setGoodsContents(goodsEntity.getGoodsContents());
        goodsDTO.setGoodsHits(goodsEntity.getGoodsHits());
        // 카테고리
        goodsDTO.setCategory(goodsEntity.getGoodsOpt());
        goodsDTO.setCreatedTime(goodsEntity.getCreatedTime());
        goodsDTO.setUpdatedTime(goodsEntity.getUpdatedTime());
        // 파일 첨부 여부 설정
        goodsDTO.setFileAttached(goodsEntity.getFileAttached());

        // 파일 첨부 정보 복사 (첨부 파일이 있을 때)
        if (goodsEntity.getFileAttached() == 1) {
            if (!goodsEntity.getGoodsFileEntityList().isEmpty()) {
                goodsDTO.setOriginalFileName(goodsEntity.getGoodsFileEntityList().get(0).getOriginalFileName());
                goodsDTO.setStoredFileName(goodsEntity.getGoodsFileEntityList().get(0).getStoredFileName());
            }
        }
        return goodsDTO;
    }
}