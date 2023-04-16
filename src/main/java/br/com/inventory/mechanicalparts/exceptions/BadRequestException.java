package br.com.inventory.mechanicalparts.exceptions;

import java.util.List;

public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public BadRequestException(String msg) {
		super(msg);
	}

	public BadRequestException(String msg, Throwable nullFields) {
		super(msg, nullFields);
	}

	public BadRequestException(String message, List<String> nullFields) {
		// TODO Auto-generated constructor stub
	}

}
