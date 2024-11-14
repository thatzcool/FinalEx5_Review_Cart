package com.ssg.finalex5_review_cart.product.repository;

import com.ssg.finalex5_review_cart.product.dto.ProductDTO;
import com.ssg.finalex5_review_cart.product.entity.ProductEntity;
import com.ssg.finalex5_review_cart.product.repository.search.ProductSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, ProductSearch {

    //  @EntityGraph(attributePaths = {"images"}, type = EntityGraph.EntityGraphType.FETCH)
    //  @Query("select p from ProductEntity p where p.pno = :pno")
    //  Optional<ProductEntity> getProduct(@Param("pno") Long pno);

    @Query("select p from ProductEntity p join fetch  p.images pi where p.pno = :pno")
    Optional<ProductEntity> getProduct(@Param("pno") Long pno);

    @Query("select p from ProductEntity p join fetch  p.images pi where p.pno = :pno")
    Optional<ProductDTO> getProductDTO(@Param("pno") Long pno);


    @Query("select p from ProductEntity p join fetch  p.images pi ")
    Page<ProductDTO> listQuery(Pageable pageable);


}