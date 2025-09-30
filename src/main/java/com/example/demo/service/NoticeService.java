package com.example.demo.service;

import com.example.demo.dto.NoticeDTO;
import com.example.demo.entity.NoticeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.repository.NoticeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public void save(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = NoticeEntity.toSaveEntity(noticeDTO);
        noticeRepository.save(noticeEntity);
    }


    public List<NoticeDTO> findAll() {
        List<NoticeEntity> noticeEntityList = noticeRepository.findAllByOrderByIdDesc();
        List<NoticeDTO> noticeDTOList = new ArrayList<>();

        int noticeNo = noticeEntityList.size();

        for(NoticeEntity noticeEntity: noticeEntityList) {
            NoticeDTO noticeDTO = NoticeDTO.toNoticeDTO(noticeEntity);
            noticeDTO.setNoticeNo(noticeNo--);
            noticeDTOList.add(noticeDTO);
        }
        return  noticeDTOList;
    }

    public List<NoticeDTO> findTop6ByOrderByIdDesc() {
        List<NoticeEntity> noticeEntityList = noticeRepository.findTop6ByOrderByIdDesc();
        List<NoticeDTO> noticeDTOList = new ArrayList<>();

        for(NoticeEntity noticeEntity: noticeEntityList) {
            noticeDTOList.add(NoticeDTO.toNoticeDTO(noticeEntity));
        }
        return noticeDTOList;
    }

    public NoticeDTO findById(Long id) {
        Optional<NoticeEntity> optionalNoticeEntity = noticeRepository.findById(id);
        if(optionalNoticeEntity.isPresent()){
            NoticeEntity noticeEntity = optionalNoticeEntity.get();
            NoticeDTO noticeDTO = NoticeDTO.toNoticeDTO(noticeEntity);
            return noticeDTO;
        } else {
            return null;
        }
    }

    public NoticeDTO update(NoticeDTO noticeDTO) {
        NoticeEntity noticeEntity = NoticeEntity.toUpdateEntity(noticeDTO);
        noticeRepository.save(noticeEntity);
        return findById(noticeDTO.getId());
    }

    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }

    public void updateHits(Long id) {
        noticeRepository.updateHits(id);
    }
}