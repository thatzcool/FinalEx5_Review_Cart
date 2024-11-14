package com.ssg.finalex5_review_cart.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductListDTO {
    private Long pno;
    private String pname;
    private int price;
    private String writer;
    private String productImage;
}
