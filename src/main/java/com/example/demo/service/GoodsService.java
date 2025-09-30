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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoodsService {
    private final GoodsRepository goodsRepository;
    private final GoodsFileRepository goodsFileRepository;

    public void save(GoodsDTO goodsDTO) throws IOException {

        if (goodsDTO.getGoodsFile() == null || goodsDTO.getGoodsFile().isEmpty()) {
            goodsDTO.setFileAttached(0);
            goodsDTO.setOriginalFileName(null);
            goodsDTO.setStoredFileName(null);
        } else {
            MultipartFile goodsFile = goodsDTO.getGoodsFile();
            String originalFileName = goodsFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFileName;
            String savePath = "C:/project_img/" + storedFileName;
            goodsFile.transferTo(new File(savePath));

            goodsDTO.setOriginalFileName(originalFileName);
            goodsDTO.setStoredFileName(storedFileName);
            goodsDTO.setFileAttached(1); // 파일 있음
        }

        GoodsEntity goodsEntity = GoodsEntity.toSaveEntity(goodsDTO);
        GoodsEntity savedGoods = goodsRepository.save(goodsEntity);

        if (goodsDTO.getFileAttached() == 1) {
            GoodsFileEntity goodsFileEntity = GoodsFileEntity.toGoodsFileEntity(
                    savedGoods,
                    goodsDTO.getOriginalFileName(),
                    goodsDTO.getStoredFileName()
            );
            goodsFileRepository.save(goodsFileEntity);
        }
    }

    @Transactional
    public Page<GoodsDTO> findAll(Pageable pageable, String category) {
        int page = pageable.getPageNumber() == 0 ? 0 : pageable.getPageNumber() - 1;
        int pageLimit = 9;

        PageRequest pageRequest = PageRequest.of(
                page,
                pageLimit,
                Sort.by(Sort.Direction.DESC, "id")
        );

        Page<GoodsEntity> goodsEntityPage;

        if (category != null && !category.equals("all")) {
            // 특정 카테고리로 필터링
            goodsEntityPage = goodsRepository.findByGoodsOpt(category, pageRequest);
        } else {
            goodsEntityPage = goodsRepository.findAll(pageRequest);
        }

        Page<GoodsDTO> goodsDTOPage = goodsEntityPage.map(GoodsDTO::toGoodsDTO);

        return goodsDTOPage;
    }

    @Transactional
    public List<GoodsDTO> findAllAdmin() {
        List<GoodsEntity> goodsEntityList = goodsRepository.findAllByOrderByIdDesc();
        List<GoodsDTO> goodsDTOList = new ArrayList<>();

        // 1. 목록의 총 개수(순번의 시작 번호)를 가져옴
        int goodsNo = goodsEntityList.size();

        for (GoodsEntity goodsEntity : goodsEntityList) {
            GoodsDTO goodsDTO = GoodsDTO.toGoodsDTO(goodsEntity);
            // 2. goodsNo를 셋팅하고 1씩 감소시킴 (가장 최신 글이 가장 낮은 번호를 갖게 됨)
            goodsDTO.setGoodsNo(goodsNo--);
            goodsDTOList.add(goodsDTO);
        }

        return goodsDTOList;
    }

    // 3. 최신 상품 6개 조회
    public List<GoodsDTO> findTop6ByOrderByIdDesc() {
        List<GoodsEntity> goodsEntityList = goodsRepository.findTop6ByOrderByIdDesc();
        List<GoodsDTO> goodsDTOList = new ArrayList<>();

        for (GoodsEntity goodsEntity : goodsEntityList) {
            goodsDTOList.add(GoodsDTO.toGoodsDTO(goodsEntity));
        }

        return goodsDTOList;
    }

    // 4. 상품 상세 조회
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

    // 5. 조회수 증가
    @Transactional
    public void goodsHits(Long id) {
        goodsRepository.updateHits(id);
    }

    // 6. 상품 수정 (텍스트만 수정, 파일 정보 유지)
    @Transactional
    public GoodsDTO update(GoodsDTO goodsDTO) throws IOException {

        Optional<GoodsEntity> optionalGoodsEntity = goodsRepository.findById(goodsDTO.getId());

        if (optionalGoodsEntity.isPresent()) {
            GoodsEntity existingEntity = optionalGoodsEntity.get();

            // 텍스트 필드만 업데이트 (파일 정보와 조회수는 existingEntity의 현재 값을 유지)
            existingEntity.setGoodsOpt(goodsDTO.getGoodsOpt());
            existingEntity.setGoodsTitle(goodsDTO.getGoodsTitle());
            existingEntity.setGoodsCost(goodsDTO.getGoodsCost());
            existingEntity.setGoodsBrand(goodsDTO.getGoodsBrand());
            existingEntity.setGoodsContents(goodsDTO.getGoodsContents());

            GoodsEntity updatedGoods = goodsRepository.save(existingEntity);

            return GoodsDTO.toGoodsDTO(updatedGoods);
        } else {
            return null;
        }
    }

    // 7. 상품 삭제
    @Transactional
    public void delete(Long id) {
        goodsRepository.deleteById(id);
    }
}