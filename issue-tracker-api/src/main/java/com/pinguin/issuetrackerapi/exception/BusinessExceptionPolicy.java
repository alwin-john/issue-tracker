package com.pinguin.issuetrackerapi.exception;

import org.springframework.http.HttpStatus;

public interface BusinessExceptionPolicy {

    String getCode();

    String getMessage();

    HttpStatus getHttpStatus();
}
