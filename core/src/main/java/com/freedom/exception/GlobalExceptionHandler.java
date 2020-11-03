package com.freedom.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = ResponseStatusException.class)
    @ResponseBody
    public ResponseEntity<ResultBody> exceptionHandler(ResponseStatusException e) {
        logger.error("未知异常！原因是:", e);
        return ResponseEntity
                .status(e.getStatus())
                .body(ResultBody.error(e.getStatus().value(), e.getReason()));
    }

}
