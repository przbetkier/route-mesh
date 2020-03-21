package dev.przbetkier.routemesh.infrastructure;

import dev.przbetkier.routemesh.domain.common.DomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@ControllerAdvice
@Order(HIGHEST_PRECEDENCE)
class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    GlobalExceptionHandler() {}

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException exc) {
        final HttpStatus status = exc.getStatus();
        logger.warn("Domain exception occurred", exc);
        return new ResponseEntity<>(new ErrorResponse(status.value(), status.getReasonPhrase(), exc.getMessage()),
                                    status);
    }
}
