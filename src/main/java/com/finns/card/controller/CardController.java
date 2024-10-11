package com.finns.card.controller;

import com.finns.card.dto.CardDetailDTO;
import com.finns.card.dto.CardProductDTO;
import com.finns.card.pagination.PageResponse;
import com.finns.card.service.CardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product/card")  // 클래스 레벨에서 새로운 경로 설정
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    // 카드 목록 조회
    @GetMapping
    public PageResponse<CardProductDTO> getCards(@RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "10") int size) {
        return cardService.getCards(page, size);
    }

    // 특정 카드 상세 조회
    @GetMapping("/{id}")
    public CardDetailDTO getCard(@PathVariable long id) {
        return cardService.getCardById(id);
    }


}

