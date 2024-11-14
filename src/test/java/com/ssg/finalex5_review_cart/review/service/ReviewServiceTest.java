package com.ssg.finalex5_review_cart.review.service;

import com.ssg.finalex5_review_cart.review.dto.ReviewDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Test
    public void testRegister(){
        Long pno = 100L;

        ReviewDTO reviewDTO = ReviewDTO.builder().reviewText("첫번째 리뷰").score(5).reviewer("ssgSYM").pno(pno).build();

        reviewService.register(reviewDTO);
    }

     @Test
    public void testRead(){
        Long rno = 100L;
        ReviewDTO reviewDTO = reviewService.read(rno);
        log.info(reviewDTO);
     }
}