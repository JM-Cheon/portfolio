package com.jm.portfolio.global.common;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ApiResponse {
    private int status;
    private String message;
    private Object data;

    public ApiResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public ApiResponse(HttpStatus status, String message, Object data) {
        this.status = status.value();
        this.message = message;
        this.data = data;
    }
}
