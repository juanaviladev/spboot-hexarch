package es.cloudapps.hexarch.hexagon.domain.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Resource not found");
    }
}
