package com.ssg.finalex5_review_cart.cart.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCartItemDTO {

    private String holder;

    private Long pno;

    private int quantity;

}