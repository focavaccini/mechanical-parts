package br.com.inventory.mechanicalparts.exceptions;

public class ObjectNotFound extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFound(String msg) {
		super(msg);
	}

	public ObjectNotFound(String msg, Throwable cause) {
		super(msg, cause);
	}

}
