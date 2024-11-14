package com.ssg.finalex5_review_cart.review.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
public class ReviewTaskException extends RuntimeException{

    private String message;
    private int code;

    public ReviewTaskException(String message, int code){
        super(message);
        this.message = message;
        this.code = code;
    }

}
