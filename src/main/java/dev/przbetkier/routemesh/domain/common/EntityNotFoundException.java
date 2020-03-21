package dev.przbetkier.routemesh.domain.common;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends DomainException {

    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public EntityNotFoundException(String entity, Long id) {
        super(String.format("Could not find %s with ID: %s", entity, id), STATUS);
    }
}
