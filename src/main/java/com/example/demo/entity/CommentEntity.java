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

    // Comment(Many) <-> Goods(One) 관계 설정
    // 외래 키를 지정할 때는 @JoinColumn을 사용해야 합니다. (이전 오류 해결 적용)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goods_id") // DB 테이블의 외래 키 컬럼 이름
    private GoodsEntity goodsEntity;

    // DTO -> Entity 변환 (저장 시 사용)
    public static CommentEntity toSaveEntity(CommentDTO commentDTO, GoodsEntity goodsEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setGoodsEntity(goodsEntity); // 관계 설정
        return commentEntity;
    }
}
