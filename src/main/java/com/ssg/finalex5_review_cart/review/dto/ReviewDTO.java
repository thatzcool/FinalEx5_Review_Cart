package com.ssg.finalex5_review_cart.review.dto;

import com.ssg.finalex5_review_cart.product.entity.ProductEntity;
import com.ssg.finalex5_review_cart.review.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ReviewDTO {

    private Long rno;

    private String reviewText;

    private String reviewer;

    private int score;

    private Long pno;

    private LocalDateTime reviewDate;

    private LocalDateTime modifiedDate;

    public ReviewDTO(ReviewEntity reviewEntity) {
        this.rno = reviewEntity.getRno();
        this.reviewText = reviewEntity.getReviewText();
        this.reviewer = reviewEntity.getReviewer();
        this.score = reviewEntity.getScore();
        this.pno = reviewEntity.getProductEntity().getPno();
        this.reviewDate = reviewEntity.getReviewDate();
        this.modifiedDate = reviewEntity.getModifiedDate();
    }

    public ReviewEntity toEntity() {

        ProductEntity productEntity = ProductEntity.builder().pno(pno).build();

        return ReviewEntity.builder()
                .rno(rno)
                .reviewText(reviewText)
                .reviewer(reviewer)
                .score(score)
                .productEntity(productEntity)
                .build();
    }
}