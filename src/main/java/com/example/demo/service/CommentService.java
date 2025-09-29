package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.GoodsEntity;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final GoodsRepository goodsRepository;

    // 댓글 저장 처리
    public Long save(CommentDTO commentDTO) {
        // 1. Goods ID로 GoodsEntity를 조회합니다.
        Optional<GoodsEntity> optionalGoodsEntity = goodsRepository.findById(commentDTO.getGoodsId());

        if (optionalGoodsEntity.isPresent()) {
            // 2. 상품(Goods) 정보가 존재하면
            GoodsEntity goodsEntity = optionalGoodsEntity.get();

            // 3. DTO와 Entity 정보를 사용하여 CommentEntity로 변환합니다.
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, goodsEntity);

            // 4. 댓글 엔티티를 저장하고 저장된 댓글의 ID를 반환합니다.
            return commentRepository.save(commentEntity).getId();
        } else {
            // 5. 상품(Goods) 정보가 없으면 null을 반환합니다.
            return null;
        }
    }

    // 상품 ID에 따른 댓글 목록 조회
    public List<CommentDTO> findAll(Long goodsId) {
        // 1. Repository를 통해 댓글 Entity 리스트 조회 (최신순 정렬)
        List<CommentEntity> commentEntityList = commentRepository.findByGoodsEntity_IdOrderByCreatedTimeDesc(goodsId);

        // 2. Entity 리스트를 DTO 리스트로 변환
        List<CommentDTO> commentDTOList = commentEntityList.stream()
                .map(CommentDTO::toCommentDTO)
                .collect(Collectors.toList());

        return commentDTOList;
    }
}
