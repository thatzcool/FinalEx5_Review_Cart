package com.ssg.finalex5_review_cart.cart.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModifyCartItemDTO {
     private Long itemNo;   //제품번호
     private int quantity;  //수량
}
