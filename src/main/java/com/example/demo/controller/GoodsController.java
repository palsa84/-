package com.example.demo.controller;

import com.example.demo.dto.GoodsDTO;
import com.example.demo.service.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller // Spring 컨트롤러 등록
@RequiredArgsConstructor
public class GoodsController {

    private final GoodsService goodsService;

    // 1. 상품 목록 보기: /goods
    @GetMapping("/goods")
    public String index(Model model) {
        List<GoodsDTO> latestGoods = goodsService.findAll();
        model.addAttribute("latestGoods", latestGoods);
        return "goods/list";   // templates/goods/list.html
    }

    // 2. 상품 상세 보기: /goods/detail/{id}
    @GetMapping("/goods/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        GoodsDTO goodsDTO = goodsService.findById(id);
        System.out.println("조회 결과: " + goodsDTO);

        goodsService.goodsHits(id); // 조회수 증가
        model.addAttribute("goods", goodsDTO);
        return "goods/detail";  // templates/goods/detail.html
    }
}
