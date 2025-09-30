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
    private Long goodsId;
    private LocalDateTime createdTime;

    public static CommentDTO toCommentDTO(CommentEntity commentEntity) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCreatedTime(commentEntity.getCreatedTime());
        return commentDTO;
    }
}
