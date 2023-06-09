package br.com.barberShop.exceptions;

public class LoginNullException extends RuntimeException {

	private static final long serialVersionUID = 8723653622319071355L;

	public LoginNullException(String msg) {
		super(msg);
	}

}
