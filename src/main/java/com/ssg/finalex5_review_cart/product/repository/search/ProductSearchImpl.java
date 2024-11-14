package com.ssg.finalex5_review_cart.product.repository.search;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.ssg.finalex5_review_cart.product.dto.ProductDTO;
import com.ssg.finalex5_review_cart.product.dto.ProductListDTO;
import com.ssg.finalex5_review_cart.product.entity.ProductEntity;
import com.ssg.finalex5_review_cart.product.entity.QProductEntity;
import com.ssg.finalex5_review_cart.product.entity.QProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {

    public ProductSearchImpl() {
        super(ProductEntity.class);
    }

    @Override

    public Page<ProductListDTO> list(Pageable pageable) {

        QProductEntity productEntity = QProductEntity.productEntity;
        QProductImage productImage = QProductImage.productImage;

        JPQLQuery<ProductEntity> query = from(productEntity);
        query.leftJoin(productEntity.images, productImage);

        //where productImage.idx = 0
        query.where(productImage.idx.eq(0));

        //Long pno, String pname, int price, String writer, String productImage
        JPQLQuery<ProductListDTO> dtojpqlQuery = query.select(Projections.bean(ProductListDTO.class,
                productEntity.pno,
                productEntity.pname,
                productEntity.price,
                productEntity.writer,
                productImage.fileName.as("productImage")));

        this.getQuerydsl().applyPagination(pageable, dtojpqlQuery);

        List<ProductListDTO> dtoList = dtojpqlQuery.fetch();

        long count = dtojpqlQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, count);
    }


    @Override


    public Page<ProductDTO> listWithAllImages(Pageable pageable) {

        QProductEntity productEntity = QProductEntity.productEntity;

        JPQLQuery<ProductEntity> query = from(productEntity);

        this.getQuerydsl().applyPagination(pageable, query);

        List<ProductEntity> entityList = query.fetch();

        long count = query.fetchCount();


        List<ProductDTO> dtoList = entityList.stream().map(ProductDTO::new).toList();

        return new PageImpl<>(dtoList, pageable, count);
    }

    @Override


    public Page<ProductDTO> listFetchAllImages(Pageable pageable) {

        QProductEntity productEntity = QProductEntity.productEntity;
        QProductImage productImage = QProductImage.productImage;

        JPQLQuery<ProductEntity> query = from(productEntity);
        query.leftJoin(productEntity.images, productImage).fetchJoin();

        this.getQuerydsl().applyPagination(pageable, query);

        List<ProductEntity> entityList = query.fetch();

        long count = query.fetchCount();


        List<ProductDTO> dtoList = entityList.stream().map(ProductDTO::new).toList();


        return new PageImpl<>(dtoList, pageable, count);

    }


}