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

    // 🌟 이전에 누락되어 오류를 발생시킨 필드입니다.
    private int goodsNo; // ⬅️ GoodsService에서 setGoodsNo(int)를 호출하기 위해 추가

    private MultipartFile goodsFile; //파일 담는 용도
    private String originalFileName; //원본파일이름
    private String storedFileName; //저장용 파일이름
    private int fileAttached; //파일 첨부 여부


// GoodsDTO.java 파일 내의 toGoodsDTO 메서드

    public static GoodsDTO toGoodsDTO(GoodsEntity goodsEntity) {
        GoodsDTO goodsDTO = new GoodsDTO(); // ⬅️ 이 객체 이름이 'goodsDTO'입니다.

        // 1. 기본 필드 복사
        goodsDTO.setId(goodsEntity.getId());
        goodsDTO.setGoodsOpt(goodsEntity.getGoodsOpt());
        goodsDTO.setGoodsTitle(goodsEntity.getGoodsTitle());
        goodsDTO.setGoodsCost(goodsEntity.getGoodsCost());
        goodsDTO.setGoodsBrand(goodsEntity.getGoodsBrand());
        goodsDTO.setGoodsContents(goodsEntity.getGoodsContents());
        goodsDTO.setGoodsHits(goodsEntity.getGoodsHits());

        // 2. BaseEntity 필드 복사
        goodsDTO.setCreatedTime(goodsEntity.getCreatedTime());
        goodsDTO.setUpdatedTime(goodsEntity.getUpdatedTime());

        // 3. 파일 첨부 여부 설정
        goodsDTO.setFileAttached(goodsEntity.getFileAttached());


        // 4. 파일 첨부 정보 복사 (첨부 파일이 있을 때만 실행)
        if (goodsEntity.getFileAttached() == 1) { // ⬅️ 이전 DTO에서는 0일 때와 1일 때 로직을 나누었습니다.

            // GoodsFileEntityList에서 파일 이름 정보를 가져와 DTO에 설정
            if (!goodsEntity.getGoodsFileEntityList().isEmpty()) {
                goodsDTO.setOriginalFileName(goodsEntity.getGoodsFileEntityList().get(0).getOriginalFileName());
                goodsDTO.setStoredFileName(goodsEntity.getGoodsFileEntityList().get(0).getStoredFileName());
            }
        }
        return goodsDTO;
    }
}