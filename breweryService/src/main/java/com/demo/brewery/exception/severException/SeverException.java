package com.demo.brewery.exception.severException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class SeverException extends RuntimeException {
    public SeverException(String msg) {
        super(msg);
    }
}
