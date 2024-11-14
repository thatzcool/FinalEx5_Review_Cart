package com.ssg.finalex5_review_cart.product.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ProductImage implements Comparable<ProductImage>{
    private int idx;

    private String fileName;
    @Override
    public int compareTo(ProductImage o) {
        return this.idx - o.idx;
    }
}
