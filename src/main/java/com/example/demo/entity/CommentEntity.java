package com.example.demo.entity;

import com.example.demo.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String commentWriter;

    @Column(length = 200, nullable = false)
    private String commentContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id")
    private GoodsEntity goodsEntity;

    public static CommentEntity toSaveEntity(CommentDTO commentDTO, GoodsEntity goodsEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setGoodsEntity(goodsEntity);
        return commentEntity;
    }
}
