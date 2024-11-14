package com.ssg.finalex5_review_cart.cart.repository;

import com.ssg.finalex5_review_cart.cart.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByHolder(String holder);

}