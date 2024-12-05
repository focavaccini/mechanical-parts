package br.com.inventory.mechanicalparts.exceptions;

public class TokenRefreshException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TokenRefreshException(String msg) {
        super(msg);
    }

    public TokenRefreshException(String msg, Throwable nullFields) {
        super(msg, nullFields);
    }

}
