package br.com.barberShop.exceptions;

import java.io.Serial;

public class LoginNullException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 8723653622319071355L;

	public LoginNullException(String msg) {
		super(msg);
	}

}
