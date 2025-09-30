package com.example.demo.controller;

import com.example.demo.dto.GoodsDTO;
import com.example.demo.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsService goodsService;

    // 상품 목록 보기
    @GetMapping("/goods")
    public String index(
            Model model,
            @PageableDefault(page = 1, size = 9, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestParam(value = "category", required = false, defaultValue = "all") String category
    ) {
        // category 파라미터와 함께 서비스 호출
        Page<GoodsDTO> goodsPage = goodsService.findAll(pageable, category);

        // 페이지네이션 Block 크기 계산
        int blockLimit = 5;
        int currentPage = goodsPage.getNumber() + 1;
        int startPage = ((currentPage - 1) / blockLimit) * blockLimit + 1;
        int endPage = Math.min(startPage + blockLimit - 1, goodsPage.getTotalPages());

        if (goodsPage.getTotalPages() == 0) {
            startPage = 1;
            endPage = 1;
        }

        model.addAttribute("goodsPage", goodsPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentCategory", category); // 현재 카테고리를 뷰에 전달

        return "goods/list";
    }


    // 상품 상세 보기
    @GetMapping("/goods/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        GoodsDTO goodsDTO = goodsService.findById(id);
        System.out.println("조회 결과: " + goodsDTO);

        goodsService.goodsHits(id); // 조회수 증가
        model.addAttribute("goods", goodsDTO);
        return "goods/detail";
    }
}