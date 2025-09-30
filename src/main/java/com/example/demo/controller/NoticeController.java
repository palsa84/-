package com.example.demo.controller;

import com.example.demo.dto.NoticeDTO;
import com.example.demo.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class NoticeController  {

    private final NoticeService noticeService;

    @GetMapping("/notice")
    public String findAll(Model model) {
        // 공지사항 목록 조회
        List<NoticeDTO> noticeDTOList = noticeService.findAll();
        model.addAttribute("noticeList", noticeDTOList);
        return "/notice/list";
    }

    @GetMapping("/notice/detail/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {

        noticeService.updateHits(id);
        NoticeDTO noticeDTO = noticeService.findById(id);
        model.addAttribute("notice", noticeDTO);
        return "/notice/detail";
    }


}