package com.example.demo.service;

import com.example.demo.dto.GoodsDTO;
import com.example.demo.entity.GoodsEntity;
import com.example.demo.entity.GoodsFileEntity;
import com.example.demo.repository.GoodsFileRepository;
import com.example.demo.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.transaction.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional; // findById를 위해 Optional 임포트 추가

@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private final GoodsFileRepository goodsFileRepository;

    // 상품 등록 및 파일 저장
    public void save(GoodsDTO goodsDTO) throws IOException {

        // 1. 첨부파일 처리 로직...
        if (goodsDTO.getGoodsFile() == null || goodsDTO.getGoodsFile().isEmpty()) {
            goodsDTO.setFileAttached(0);
        } else {
            MultipartFile goodsFile = goodsDTO.getGoodsFile();
            String originalFileName = goodsFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
            String savePath = "C:/project_img/" + storedFileName;
            goodsFile.transferTo(new File(savePath));
            goodsDTO.setOriginalFileName(originalFileName);
            goodsDTO.setStoredFileName(storedFileName);
            goodsDTO.setFileAttached(1);
        }

        // 2. Entity 저장 로직...
        GoodsEntity goodsEntity = GoodsEntity.toSaveEntity(goodsDTO);
        GoodsEntity savedGoods = goodsRepository.save(goodsEntity);

        // 3. GoodsFileEntity 저장 로직...
        if (goodsDTO.getFileAttached() == 1) {
            GoodsFileEntity goodsFileEntity = GoodsFileEntity.toGoodsFileEntity(
                    savedGoods,
                    goodsDTO.getOriginalFileName(),
                    goodsDTO.getStoredFileName()
            );
            goodsFileRepository.save(goodsFileEntity);
        }
    }

    // 1. 상품 목록 전체 조회 및 순번 부여 (오류 수정 완료)
    @Transactional
    public List<GoodsDTO> findAll() {
        List<GoodsEntity> goodsEntityList = goodsRepository.findAllByOrderByIdDesc();
        List<GoodsDTO> goodsDTOList = new ArrayList<>();

        int goodsNo = goodsEntityList.size();

        for (GoodsEntity goodsEntity : goodsEntityList) {
            // 1. Entity를 DTO로 변환
            GoodsDTO goodsDTO = GoodsDTO.toGoodsDTO(goodsEntity);

            // 2. DTO에 순번을 부여하고 번호를 1 감소
            goodsDTO.setGoodsNo(goodsNo--);

            // 3. 최종 DTO를 리스트에 추가 (한 번만 추가)
            goodsDTOList.add(goodsDTO);
        }

        return goodsDTOList;
    }

    // 2. 최신 상품 6개 조회
    public List<GoodsDTO> findTop6ByOrderByIdDesc() {
        List<GoodsEntity> goodsEntityList = goodsRepository.findTop6ByOrderByIdDesc();
        List<GoodsDTO> goodsDTOList = new ArrayList<>();

        for (GoodsEntity goodsEntity : goodsEntityList) {
            goodsDTOList.add(GoodsDTO.toGoodsDTO(goodsEntity));
        }

        return goodsDTOList;
    }

    // 3. 상품 상세 조회 (GoodsController에서 사용됨)
    @Transactional
    public GoodsDTO findById(Long id) {
        Optional<GoodsEntity> optionalGoodsEntity = goodsRepository.findById(id);
        if (optionalGoodsEntity.isPresent()) {
            GoodsEntity goodsEntity = optionalGoodsEntity.get();
            GoodsDTO goodsDTO = GoodsDTO.toGoodsDTO(goodsEntity);
            return goodsDTO;
        } else {
            return null;
        }
    }



    // 4. 조회수 증가 (GoodsController에서 사용됨) - 이 메서드는 Repository에 @Modifying 쿼리가 필요함
    @Transactional
    public void goodsHits(Long id) {
        goodsRepository.updateHits(id);
    }
}