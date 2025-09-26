package com.example.demo.dto;

import com.example.demo.entity.NoticeEntity;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class NoticeDTO {
    private Long id;
    private String noticeTitle;
    private String noticeWriter;
    private String noticeContents;
    private int noticeHits;
    private LocalDateTime noticeCreatedTime;
    private LocalDateTime noticeUpdatedTime;

    private int noticeNo; //화면용 글 번호

    public static NoticeDTO toNoticeDTO(NoticeEntity noticeEntity) {
        NoticeDTO noticeDTO = new NoticeDTO();
        noticeDTO.setId(noticeEntity.getId());
        noticeDTO.setNoticeWriter(noticeEntity.getNoticeWriter());
        noticeDTO.setNoticeTitle(noticeEntity.getNoticeTitle());
        noticeDTO.setNoticeContents(noticeEntity.getNoticeContents());
        noticeDTO.setNoticeHits(noticeEntity.getNoticeHits());
        noticeDTO.setNoticeCreatedTime(noticeEntity.getCreatedTime());
        noticeDTO.setNoticeUpdatedTime(noticeEntity.getUpdatedTime());
        return noticeDTO;
    }

}
