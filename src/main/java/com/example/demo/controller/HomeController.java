package com.example.demo.controller;

import com.example.demo.dto.GoodsDTO;
import com.example.demo.dto.NoticeDTO;
import com.example.demo.service.GoodsService;
import com.example.demo.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final NoticeService noticeService;
    private final GoodsService goodsService;

    public HomeController(NoticeService noticeService, GoodsService goodsService) {
        this.noticeService = noticeService;
        this.goodsService = goodsService;
    }

    @GetMapping("/")
    public String index(Model model) {
        // 최신 공지사항 6개 조회
        List<NoticeDTO> noticeList = noticeService.findTop6ByOrderByIdDesc();
        model.addAttribute("noticeList", noticeList);

        // 최신 상품 6개 조회
        List<GoodsDTO> latestGoods = goodsService.findTop6ByOrderByIdDesc();
        model.addAttribute("latestGoods", latestGoods);

        return "index";
    }
}