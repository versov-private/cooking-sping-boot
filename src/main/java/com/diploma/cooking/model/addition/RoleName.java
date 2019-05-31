package com.diploma.cooking.model.addition;

public enum  RoleName {
    ROLE_USER("User"),
    ROLE_ADMIN("Admin");

    private final String name;

    RoleName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
