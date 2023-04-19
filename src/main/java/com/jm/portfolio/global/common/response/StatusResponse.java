package com.jm.portfolio.global.common.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StatusResponse {
    private int status;
    private String message;
    private Object data;

    public StatusResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public StatusResponse(HttpStatus status, String message, Object data) {
        this.status = status.value();
        this.message = message;
        this.data = data;
    }
}
