package br.com.barberShop.constants;

public enum ConstantesAutentication {

	AUTHORIZATION("Authorization"),
	BEARER("Bearer "),
	ROLES_CLAIM ("Roles"),
	FULL_NAME("fullName"),
	ROLE("ROLE_");

	private final String message;

	ConstantesAutentication(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

}