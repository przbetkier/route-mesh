package dev.przbetkier.routemesh.domain.obstacle;

import dev.przbetkier.routemesh.domain.common.DomainException;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

class UnsupportedObstacleTypeException extends DomainException {

    private static final HttpStatus STATUS_CODE = UNPROCESSABLE_ENTITY;

    public UnsupportedObstacleTypeException(String message) {
        super(message, STATUS_CODE);
    }
}
