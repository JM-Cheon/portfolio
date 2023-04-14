package com.jm.portfolio.global.common.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseDTO {
    private int status;
    private String message;
    private Object data;

    public ResponseDTO (HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public ResponseDTO (HttpStatus status, String message, Object data) {
        this.status = status.value();
        this.message = message;
        this.data = data;
    }
}
