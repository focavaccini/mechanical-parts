package br.com.inventory.mechanicalparts.exceptions;

public class UniqueException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public UniqueException(String msg) {
		super(msg);
	}

	public UniqueException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
