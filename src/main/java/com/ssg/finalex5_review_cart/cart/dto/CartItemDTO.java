package com.ssg.finalex5_review_cart.cart.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemDTO {


    private Long itemNo;


    private Long pno;


    private String pname;


    private int quantity;


    private int price;


    private String image;

}