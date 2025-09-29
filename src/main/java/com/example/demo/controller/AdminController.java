package com.example.demo.controller;

import com.example.demo.dto.GoodsDTO;
import com.example.demo.dto.NoticeDTO;
import com.example.demo.service.GoodsService;
import com.example.demo.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
// Pageable, PageableDefault, Sort 관련 import는 필요 없으므로 제거되거나 사용되지 않습니다.

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final NoticeService noticeService;
    private final GoodsService goodsService;

    @GetMapping("/")
    public String admin() {
        return "/admin/login";
    }

    @PostMapping("/ok")
    public String ok() {
        return "/admin/main";
    }

    @GetMapping("/main")
    public String main() {
        return "/admin/main";
    }

    @GetMapping("/notice")
    public String findAll(Model model) {
        List<NoticeDTO> noticeDTOList = noticeService.findAll();
        model.addAttribute("noticeList", noticeDTOList);
        return "/admin/notice/list";
    }

    @GetMapping("/notice/write")
    public String notice_write() {
        return "/admin/notice/write";
    }

    @PostMapping("/notice/ok")
    public String notice_ok(@ModelAttribute NoticeDTO noticeDTO) {
        System.out.println("noticeDTO = " + noticeDTO);
        noticeService.save(noticeDTO);
        return "redirect:/admin/notice";
    }

    @GetMapping("/notice/detail/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        NoticeDTO noticeDTO = noticeService.findById(id);
        model.addAttribute("notice", noticeDTO);
        return "/admin/notice/detaill";
    }

    @GetMapping("/notice/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        NoticeDTO noticeDTO = noticeService.findById(id);
        model.addAttribute("noticeEdit", noticeDTO);
        return "/admin/notice/edit";
    }

    @PostMapping("/notice/edit")
    public String edit(@ModelAttribute NoticeDTO noticeDTO, Model model) {
        NoticeDTO notice = noticeService.update(noticeDTO);
        model.addAttribute("notice", notice);
        return "redirect:/admin/notice/detail/" + noticeDTO.getId();
    }

    @GetMapping("/notice/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        noticeService.delete(id);
        return "redirect:/admin/notice";
    }

    @GetMapping("/goods/write")
    public String goodsForm() {
        return "/admin/goods/write";
    }

    @PostMapping("/goods/ok")
    public String goodsSave(@ModelAttribute GoodsDTO goodsDTO) throws IOException {
        goodsService.save(goodsDTO);
        return "redirect:/admin/goods/list";
    }


    // 🌟 수정: 관리자 목록 복구 (페이지네이션 관련 코드 삭제)
    @GetMapping("/goods/list")
    public String findAll1(Model model) {
        List<GoodsDTO> goodsDTOList = goodsService.findAllAdmin(); // findAllAdmin 호출
        model.addAttribute("goodsList", goodsDTOList);
        return "/admin/goods/list";
    }

    // 🌟 상품 수정 및 삭제 관련 메서드는 유지합니다.
    @GetMapping("/goods/edit/{id}")
    public String goodsEditForm(@PathVariable("id") Long id, Model model) {
        GoodsDTO goodsDTO = goodsService.findById(id);
        model.addAttribute("goodsEdit", goodsDTO);
        return "/admin/goods/edit";
    }

    @PostMapping("/goods/edit")
    public String goodsUpdate(@ModelAttribute GoodsDTO goodsDTO) throws IOException {
        goodsService.update(goodsDTO);
        return "redirect:/admin/goods/list";
    }

    @GetMapping("/goods/delete/{id}")
    public String goodsDelete(@PathVariable("id") Long id) {
        goodsService.delete(id);
        return "redirect:/admin/goods/list";
    }
}