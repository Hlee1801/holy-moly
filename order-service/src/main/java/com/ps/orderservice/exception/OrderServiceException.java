package com.ps.orderservice.exception;

import lombok.Getter;

@Getter
public class OrderServiceException extends RuntimeException {
    private final String code;

    public OrderServiceException(String code, String message) {
        super(message);
        this.code = code;
    }
}
