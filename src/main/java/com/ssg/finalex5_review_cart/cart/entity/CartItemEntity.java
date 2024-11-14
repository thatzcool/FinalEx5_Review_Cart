package com.ssg.finalex5_review_cart.cart.entity;

import com.ssg.finalex5_review_cart.product.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_cart_items", indexes = @Index(columnList = "cart_cno"))
@Getter
@ToString(exclude = {"product", "cart"})
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CartItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemNo;


    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private CartEntity cart;

    public void changeQuantity(int quantity) {
        this.quantity = quantity;
    }
}