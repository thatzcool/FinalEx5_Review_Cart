package com.ssg.finalex5_review_cart.repository;

import com.ssg.finalex5_review_cart.product.dto.ProductDTO;
import com.ssg.finalex5_review_cart.product.dto.ProductListDTO;
import com.ssg.finalex5_review_cart.product.entity.ProductEntity;
import com.ssg.finalex5_review_cart.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepoistory;

    @Test
    @Transactional
    @Commit
    public void testInsert() {

        for (int i = 1; i <=50; i++) {

            ProductEntity productEntity = ProductEntity.builder()
                    .pname(i + "_새로운 상품")
                    .price(5000)
                    .content(i + "_상품 설명")
                    .writer("user")
                    .build();

            productEntity.addImage(i + "_test1.jpg");
            productEntity.addImage(i + "_test2.jpg");

            productRepoistory.save(productEntity);

            System.out.println("New Product no " + productEntity.getPno());

        }

    }

    @Test
    @Transactional(readOnly = true)
    public void testRead() {

        Long pno = 1L;

        Optional<ProductEntity> result = productRepoistory.findById(pno);

        ProductEntity productEntity = result.get();

        System.out.println(productEntity);

        System.out.println("--------------------------------");

        System.out.println(productEntity.getImages());

    }

    @Test
    @Transactional(readOnly = true)
    public void testReadQuery() {

        Long pno = 1L;

        Optional<ProductEntity> result = productRepoistory.getProduct(pno);

        ProductEntity productEntity = result.get();

        System.out.println(productEntity);

        System.out.println("--------------------------------");

        System.out.println(productEntity.getImages());

    }

    @Test
    @Transactional
    @Commit
    public void testUpdate() {


        Optional<ProductEntity> result = productRepoistory.getProduct(1L);

        ProductEntity productEntity = result.get();

        productEntity.changePname("변경된 상품");

        productEntity.changePrice(10000);

        productEntity.addImage("new1.jpg");

        productEntity.addImage("new2.jpg");

        //변경 감지시에는 필요없음
        //productRepoistory.save(productEntity);
    }

    @Test
    @Transactional
    @Commit
    public void testDelete() {

        productRepoistory.deleteById(1L);

    }


    @Test
    public void testReadDTO() {

        //반드시 DB에 있는 번호로
        Long pno = 1L;

        Optional<ProductDTO> result = productRepoistory.getProductDTO(pno);

        ProductDTO productDTO = result.get();

        System.out.println(productDTO);

    }

    @Test
    public void testList() {

        Pageable pageable
                = PageRequest.of(0,10 , Sort.by("pno").descending());

        Page<ProductListDTO> result = productRepoistory.list(pageable);

        result.getContent().forEach(productListDTO -> {
            System.out.println(productListDTO);
        });

    }

    @Transactional
    @Test
    public void testListWithAllImages() {

        Pageable pageable = PageRequest.of(0,10 , Sort.by("pno").descending());

        Page<ProductDTO> result = productRepoistory.listWithAllImages(pageable);

        result.getContent().forEach(productDTO -> {
            System.out.println(productDTO);
        });

    }

    //@Transactional(readOnly = true)
    //  @Test
    //  public void testListFetchAllImages() {
    //
    //    Pageable pageable = PageRequest.of(, , Sort.by("pno").descending());
    //
    //    Page<ProductDTO> result = productRepoistory.listFetchAllImages(pageable);
    //
    //  }

    //@Transactional(readOnly = true)
    @Test
    public void testListFetchAllImages() {

        Pageable pageable = PageRequest.of(0,10 , Sort.by("pno").descending());

        Page<ProductDTO> result = productRepoistory.listFetchAllImages(pageable);

        for (ProductDTO productDTO : result.getContent()) {
            System.out.println(productDTO);
        }

    }



}