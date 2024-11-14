package com.ssg.finalex5_review_cart.review.controller.advice;

import com.ssg.finalex5_review_cart.review.exception.ReviewTaskException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ReviewControllerAdvice {

    @ExceptionHandler(ReviewTaskException.class)
    public ResponseEntity<Map<String, String>> handleReviewTaskException(ReviewTaskException exception) {

        int status = exception.getCode();
        String message = exception.getMessage();

        return ResponseEntity.status(status).body(Map.of("error", message));
    }
}