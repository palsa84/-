package com.example.demo.dto;

import com.example.demo.entity.CommentEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long goodsId; // 어떤 상품에 달린 댓글인지 식별하기 위한 필드
    private LocalDateTime createdTime;

    // Entity -> DTO 변환 (댓글 목록 조회 시 사용)
    public static CommentDTO toCommentDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        // goodsId는 Entity에서 GoodsEntity를 통해 가져와야 하지만, 현재는 저장 로직에 집중합니다.
        commentDTO.setCreatedTime(commentEntity.getCreatedTime());
        return commentDTO;
    }
}
