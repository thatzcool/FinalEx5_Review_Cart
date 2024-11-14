package com.ssg.finalex5_review_cart.product.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name = "tbl_products")
@Getter
@ToString(exclude = "images")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(value = { AuditingEntityListener.class })
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long pno;


     private String pname;

     private int price;

     private String content;

     private String writer;
     @CreatedDate
     private LocalDateTime joinDate;

     @LastModifiedDate
     private LocalDateTime modifiedDate;

     @ElementCollection(fetch = FetchType.LAZY)
     @CollectionTable(name = "tbl_product_images", joinColumns = @JoinColumn(name="pno"))
     @Builder.Default
     @BatchSize(size=100)
     private SortedSet<ProductImage> images = new TreeSet<>();

    public void changePrice(int price) {
        this.price = price;
    }

    public void changePname(String pname){
        this.pname = pname;
    }
    public void clearImages() {

        this.images.clear();
    }

    public void changeContent(String content) {
        this.content = content;
    }

    public void addImage(String fileName){
         ProductImage productImage = ProductImage.builder()
                 .fileName(fileName)
                 .idx(images.size())
                 .build();
         images.add(productImage);


     }


}
