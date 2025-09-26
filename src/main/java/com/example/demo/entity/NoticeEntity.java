package com.example.demo.entity;

import com.example.demo.dto.NoticeDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="notice")
public class NoticeEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30)
    private String noticeWriter;
    @Column
    private String noticeTitle;
    @Column(length = 500)
    private String noticeContents;
    @Column
    private int noticeHits;

    public static NoticeEntity toSaveEntity(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity= new NoticeEntity();
        noticeEntity.setNoticeWriter(noticeDTO.getNoticeWriter());
        noticeEntity.setNoticeTitle(noticeDTO.getNoticeTitle());
        noticeEntity.setNoticeContents(noticeDTO.getNoticeContents());
        noticeEntity.setNoticeHits(0);
        return noticeEntity;
    }

    public static NoticeEntity toUpdateEntity(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity= new NoticeEntity();
        noticeEntity.setId(noticeDTO.getId());
        noticeEntity.setNoticeWriter(noticeDTO.getNoticeWriter());
        noticeEntity.setNoticeTitle(noticeDTO.getNoticeTitle());
        noticeEntity.setNoticeContents(noticeDTO.getNoticeContents());
        noticeEntity.setNoticeHits(noticeDTO.getNoticeHits());
        return noticeEntity;
    }
}
