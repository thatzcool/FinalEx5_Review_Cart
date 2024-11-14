package com.ssg.finalex5_review_cart.review.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.ssg.finalex5_review_cart.product.entity.ProductEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_reviews", indexes = @Index(columnList = "product_pno"))
@Getter
@ToString(exclude = "productEntity")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(value = { AuditingEntityListener.class })
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String reviewText;

    private String reviewer;

    private int score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_pno")
    private ProductEntity productEntity;

    @CreatedDate
    private LocalDateTime reviewDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;


    public void changeReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void changeScore(int score) {
        this.score = score;
    }

}
