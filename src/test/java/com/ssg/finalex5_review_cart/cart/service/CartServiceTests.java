package com.ssg.finalex5_review_cart.cart.service;

import com.ssg.finalex5_review_cart.cart.dto.AddCartItemDTO;
import com.ssg.finalex5_review_cart.cart.dto.CartItemDTO;
import com.ssg.finalex5_review_cart.cart.dto.ModifyCartItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CartServiceTests {


    @Autowired
    private CartService cartService;

    @Test
    public void testGetCartList() {

        String mid = "user22";

        List<CartItemDTO> cartList = cartService.getAllItems(mid);

        cartList.forEach(cartItemDTO -> {
            System.out.println(cartItemDTO);
        });
    }

    @Test
    public void testRegisterItem() {

        String mid = "user55";
        Long pno = 40L;
        int qty = 2;

        AddCartItemDTO addCartItemDTO = AddCartItemDTO.builder()
                .holder(mid)
                .pno(pno)
                .quantity(qty)
                .build();

        cartService.registerItem(addCartItemDTO);
    }

    @Test
    public void testModifyItem() {

        Long itemNo = 2L;
        int qty = 1;

        ModifyCartItemDTO modifyCartItemDTO = ModifyCartItemDTO.builder()
                .itemNo(itemNo)
                .quantity(qty)
                .build();

        cartService.modifyItem(modifyCartItemDTO);
    }


}