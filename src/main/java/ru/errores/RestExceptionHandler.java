package ru.errores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseStatusExceptionHandler
        {

    @ExceptionHandler({ProductException.class, CartException.class})
    public ResponseEntity<ApiError> handleException(RuntimeException ex) {
        return ResponseEntity.internalServerError()
                .body(
                        new ApiError(
                                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                                "Something went wrong",
                                ex.getMessage()
                        )
                );
    }
}
