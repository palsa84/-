package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comment")  // Ajax 경로와 일치시킴
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 저장 (Ajax)
     * 요청: POST /comment/save
     * 파라미터: commentWriter, commentContents, goodsId
     * 응답: 저장된 상품의 댓글 전체 목록(JSON)
     */
    @PostMapping("/save")
    @ResponseBody
    public List<CommentDTO> save(@ModelAttribute CommentDTO commentDTO) {
        commentService.save(commentDTO);
        return commentService.findAll(commentDTO.getGoodsId());
    }

    /**
     * 상품별 댓글 목록 조회 (Ajax)
     * 요청: GET /comment/list/{goodsId}
     * 응답: 해당 상품의 댓글 전체 목록(JSON)
     */
    @GetMapping("/list/{goodsId}")
    @ResponseBody
    // 🌟 수정! @PathVariable에 경로 변수 이름("goodsId")을 명시합니다.
    public List<CommentDTO> findAll(@PathVariable("goodsId") Long goodsId) {
        return commentService.findAll(goodsId);
    }
}
