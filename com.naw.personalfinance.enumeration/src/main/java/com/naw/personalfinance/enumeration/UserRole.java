package com.naw.personalfinance.enumeration;

public enum UserRole {
	ADMIN("ADMIN"), OPERATOR("OPERATOR");

	private String name;

	private UserRole(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
