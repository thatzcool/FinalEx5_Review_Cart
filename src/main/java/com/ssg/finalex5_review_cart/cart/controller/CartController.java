package com.ssg.finalex5_review_cart.cart.controller;

import com.ssg.finalex5_review_cart.cart.dto.AddCartItemDTO;
import com.ssg.finalex5_review_cart.cart.dto.CartItemDTO;
import com.ssg.finalex5_review_cart.cart.dto.ModifyCartItemDTO;
import com.ssg.finalex5_review_cart.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/carts")


public class CartController {

    private final CartService cartService;

    //Gradle 환경 주의
    @PreAuthorize("authentication.name == #addCartItemDTO.holder")
    @PostMapping("/addItem")

    public ResponseEntity<List<CartItemDTO>> addItem(@RequestBody AddCartItemDTO addCartItemDTO) {
        log.info("add item...............");

        cartService.registerItem(addCartItemDTO);

        List<CartItemDTO> cartItemList = cartService.getAllItems(addCartItemDTO.getHolder());

        return ResponseEntity.ok(cartItemList);
    }

    @GetMapping("/{cno}")

    public ResponseEntity<List<CartItemDTO>> getCartItemList(@PathVariable("cno") Long cno, Principal principal) {
        log.info("get cart ..............." + cno);

        String mid = principal.getName();

        cartService.checkCartHolder(mid, cno);

        List<CartItemDTO> cartList = cartService.getAllItems(mid);

        return ResponseEntity.ok(cartList);
    }


    @PutMapping("/modifyItem/{itemNo}")


    public ResponseEntity<List<CartItemDTO>> modifyItem(@PathVariable("itemNo") Long itemNo,
                                                        @RequestBody ModifyCartItemDTO modifyCartItemDTO,
                                                        Principal principal) {

        log.info("modify item..............." + modifyCartItemDTO);

        String mid = principal.getName();

        log.info("mid: " + mid);

        cartService.checkItemHolder(mid, itemNo);

        modifyCartItemDTO.setItemNo(itemNo);

        cartService.modifyItem(modifyCartItemDTO);

        List<CartItemDTO> cartList = cartService.getAllItems(mid);

        return ResponseEntity.ok(cartList);
    }


    @DeleteMapping("/removeItem/{itemNo}")
    public ResponseEntity<List<CartItemDTO>> removeItem(@PathVariable("itemNo") Long itemNo, Principal principal) {
        log.info("remove item..............." + itemNo);

        String mid = principal.getName();

        cartService.checkItemHolder(mid, itemNo);

        cartService.modifyItem(ModifyCartItemDTO.builder().itemNo(itemNo).quantity(0).build() );

        List<CartItemDTO> cartList = cartService.getAllItems(mid);

        return ResponseEntity.ok(cartList);
        }

}