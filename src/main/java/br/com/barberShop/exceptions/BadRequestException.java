package br.com.barberShop.exceptions;

public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BadRequestException() {
    }

    public BadRequestException(String msg) {
        super(msg);
    }
}