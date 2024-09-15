package com.ai.devs.ai_devs.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class CustomErrorException extends RuntimeException {
    private HttpStatus status = null;
    private LocalDateTime data = null;

    public CustomErrorException() {
        super();
    }
    public CustomErrorException(String message) {
        super(message);
    }
    public CustomErrorException(HttpStatus status, String message) {
        this(message);
        this.status = status;
    }
    public CustomErrorException(HttpStatus status, String message, LocalDateTime data) {
        this(status,message);
        this.data = data;
    }
}
