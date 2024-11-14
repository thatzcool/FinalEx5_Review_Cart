package com.ssg.finalex5_review_cart.cart.repository;

import com.ssg.finalex5_review_cart.cart.entity.CartEntity;
import com.ssg.finalex5_review_cart.cart.entity.CartItemEntity;
import com.ssg.finalex5_review_cart.product.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)

public class CartRepositoryTests {

    @Autowired
    private CartRepository cartRepository;

    @Autowired

    private CartItemRepository cartItemRepository;

    @Transactional
    @Test
    @Commit


    public void testInsertCart() {

       String mid = "user22";
        Long pno = 48L;
        int qty = 3;

       Optional<CartEntity> cartResult = cartRepository.findByHolder(mid);

        CartEntity cartEntity = cartResult.orElseGet(() -> {

            CartEntity cart = CartEntity.builder().holder(mid).build();

            return cartRepository.save(cart);
           });

       ProductEntity productEntity = ProductEntity.builder().pno(pno).build();

       CartItemEntity cartItemEntity = CartItemEntity.builder()
                    .cart(cartEntity)
                    .product(productEntity)
                    .quantity(qty)
                    .build();

        cartItemRepository.save(cartItemEntity);
        }

    @Test


    public void testRead() {

       String mid = "user22";

        Optional<List<CartItemEntity>> result = cartItemRepository.getCartItemsOfHolder(mid);

       List<CartItemEntity> cartItemEntityList = result.orElse(null);

        cartItemEntityList.forEach(cartItemEntity -> {
            System.out.println(cartItemEntity);
           System.out.println(cartItemEntity.getProduct());
           System.out.println(cartItemEntity.getProduct().getImages());
           System.out.println("--------------------------------");
           });

        }


    @Test

    public void testRead2() {

       String mid = "user22";

        List<Object[]> result = cartItemRepository.getCartItemsOfHolder2(mid);

       result.forEach(arr -> {
            System.out.println(arr[0]);
            System.out.println(arr[1]);
            System.out.println(arr[2]);
            System.out.println("--------------------------------");
            });
        }

    @Test
    @Transactional
    @Commit


    public void testModifyCartItem() {

        Long itemNo = 3L;
        int qty = 0;

       Optional<CartItemEntity> result = cartItemRepository.findById(itemNo);

        CartItemEntity cartItemEntity = result.get();

        cartItemEntity.changeQuantity(qty);

       if (cartItemEntity.getQuantity() <= 0) {
         cartItemRepository.delete(cartItemEntity);
           }

       }




}