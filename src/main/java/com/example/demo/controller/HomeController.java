package com.example.demo.controller;

import com.example.demo.dto.GoodsDTO; // GoodsDTO import 추가
import com.example.demo.dto.NoticeDTO;
import com.example.demo.service.GoodsService;
import com.example.demo.service.NoticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Model import 추가
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
//@RequiredArgsConstructor
public class HomeController {
    private final NoticeService noticeService;
    private final GoodsService goodsService;

    public HomeController(NoticeService noticeService, GoodsService goodsService) {
        this.noticeService = noticeService;
        this.goodsService = goodsService;
    }

    @GetMapping("/")
    public String index(Model model) { // Model 파라미터 추가
        // 1. 최신 공지사항 6개 조회 및 모델에 추가
        List<NoticeDTO> noticeList = noticeService.findTop6ByOrderByIdDesc();
        model.addAttribute("noticeList", noticeList);

        // 2. 최신 상품 6개 조회 및 모델에 추가
        List<GoodsDTO> latestGoods = goodsService.findTop6ByOrderByIdDesc(); // latestGoods 변수 선언 및 할당
        model.addAttribute("latestGoods", latestGoods);

        return "index"; // → templates/index.html 파일을 찾아서 렌더링
    }
}