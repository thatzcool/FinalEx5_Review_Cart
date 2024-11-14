package com.ssg.finalex5_review_cart.product.repository.search;

import com.ssg.finalex5_review_cart.product.dto.ProductDTO;
import com.ssg.finalex5_review_cart.product.dto.ProductListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductSearch {

          Page<ProductListDTO> list(Pageable pageable);

          Page<ProductDTO> listWithAllImages(Pageable pageable);

        Page<ProductDTO> listFetchAllImages(Pageable pageable);

        }