package com.ssg.finalex5_review_cart.review.service;

import com.ssg.finalex5_review_cart.review.dto.ReviewDTO;
import com.ssg.finalex5_review_cart.review.dto.ReviewPageRequestDTO;
import com.ssg.finalex5_review_cart.review.entity.ReviewEntity;
import com.ssg.finalex5_review_cart.review.exception.ReviewExceptions;
import com.ssg.finalex5_review_cart.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class ReviewService {


    private final ReviewRepository reviewRepository;




    public ReviewDTO register(ReviewDTO reviewDTO) {

        log.info("review register............");

        try {
            ReviewEntity reviewEntity = reviewDTO.toEntity();

            reviewRepository.save(reviewEntity);

            return new ReviewDTO(reviewEntity);

        } catch (DataIntegrityViolationException e) {
            //외래 키 위반
            throw ReviewExceptions.REVIEW_PRODUCT_NOT_FOUND.get();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw ReviewExceptions.REVIEW_NOT_REGISTERED.get();
        }//end catch
    }



    public ReviewDTO read(Long rno) {

        ReviewEntity reviewEntity = reviewRepository.findById(rno)
                .orElseThrow(ReviewExceptions.REVIEW_NOT_FOUND::get);

        return new ReviewDTO(reviewEntity);
    }




    public void remove(Long rno) {

        ReviewEntity reviewEntity = reviewRepository.findById(rno)
                .orElseThrow(ReviewExceptions.REVIEW_NOT_FOUND::get);
        try {
            reviewRepository.delete(reviewEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw ReviewExceptions.REVIEW_NOT_REMOVED.get();
        }
    }



    public ReviewDTO modify(ReviewDTO reviewDTO) {

        ReviewEntity reviewEntity = reviewRepository.findById(reviewDTO.getRno())
                .orElseThrow(ReviewExceptions.REVIEW_NOT_FOUND::get);

        try {
            reviewEntity.changeReviewText(reviewDTO.getReviewText());
            reviewEntity.changeScore(reviewDTO.getScore());

            return new ReviewDTO(reviewEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw ReviewExceptions.REVEIW_NOT_MODIFIED.get();
        }//end catch
    }




    public Page<ReviewDTO> getList(ReviewPageRequestDTO reviewPageRequestDTO) {

        Long pno = reviewPageRequestDTO.getPno();
        Pageable pageable = reviewPageRequestDTO.getPageable(Sort.by("rno").descending());

        return reviewRepository.getListByPno(pno,pageable);
    }



}