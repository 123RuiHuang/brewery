package com.demo.brewery.exception.clientException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ClientException extends RuntimeException {
    public ClientException(String msg) {
        super(msg);
    }
}
