package br.com.barberShop.constants;

public enum ErrorCodes {

	JWT_EXPIRED("JWT Token has expired"),
	LOGIN_NULL("Login NULL"),
	LOGIN_OR_PASSWORD_IS_INVALID("Login or Password is Invalid"),
	UNABLE_TO_GET_JWT("Unable to get JWT Token"),
	INTERNAL_SERVER_ERROR("Internal server error"),
	NOT_FOUND("Not found"),
	JWT_TOKEN_NOT_BEARER_STRING("JWT Token does not begin with Bearer String"),
	UNAUTHORIZED("Unauthorized"),
	BAD_REQUEST("Bad request");

	private final String message;
	
	ErrorCodes(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

}