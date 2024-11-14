package com.ssg.finalex5_review_cart.review.controller;

import com.ssg.finalex5_review_cart.review.dto.ReviewDTO;
import com.ssg.finalex5_review_cart.review.dto.ReviewPageRequestDTO;
import com.ssg.finalex5_review_cart.review.exception.ReviewExceptions;
import com.ssg.finalex5_review_cart.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
public class ReviewController {


    private final ReviewService reviewService;


    @PostMapping("")


    public ResponseEntity<ReviewDTO> register(@RequestBody @Validated ReviewDTO reviewDTO, Principal principal) {

      log.info("register: " + reviewDTO);

        if (!principal.getName().equals(reviewDTO.getReviewer())) {
          throw ReviewExceptions.REVIEWER_MISMATCH.get();
        }

          //ReviewTaskException은 RuntimeException계열이므로 별도의 예외처리가 필요하지 않다.
       return ResponseEntity.ok(reviewService.register(reviewDTO));
       }


    @GetMapping("/{rno}")


    public ResponseEntity<ReviewDTO> read(@PathVariable("rno") Long rno) {

      log.info("read: " + rno);

       return ResponseEntity.ok(reviewService.read(rno));
     }


    @DeleteMapping("/{rno}")


    public ResponseEntity<Map<String, String>> remove(@PathVariable("rno") Long rno, Authentication authentication) {

    log.info("remove: " + rno);

       String currentUser = authentication.getName();

      log.info("currentUser: " + currentUser);

       ReviewDTO reviewDTO = reviewService.read(rno);

      if (!currentUser.equals(reviewDTO.getReviewer())) {
           throw ReviewExceptions.REVIEWER_MISMATCH.get();
           }

      reviewService.remove(rno);

      return ResponseEntity.ok().body(Map.of("result", "success"));
        }


    @PutMapping("/{rno}")


    public ResponseEntity<ReviewDTO> modify(
@PathVariable("rno") Long rno,
@RequestBody ReviewDTO reviewDTO,
Authentication authentication) {

       log.info("modify: " + rno);

        //번호 체크
      if (!rno.equals(reviewDTO.getRno())) {
         throw ReviewExceptions.REVIVEW_NOT_MATCHED.get();
          }

     String currentUser = authentication.getName();
       log.info("currentUser: " + currentUser);

        if (!currentUser.equals(reviewDTO.getReviewer())) {
           throw ReviewExceptions.REVIEWER_MISMATCH.get();
          }

        return ResponseEntity.ok().body(reviewService.modify(reviewDTO));
       }



    @GetMapping("/{pno}/list")


    public ResponseEntity<?> list(@PathVariable("pno") Long pno,
@Validated ReviewPageRequestDTO pageRequestDTO) {

      pageRequestDTO.setPno(pno);

      return ResponseEntity.ok(reviewService.getList(pageRequestDTO));

       }



}
