package com.wms.domain.user.entity;

public enum Role {
    USER("USER"), ADMIN("ADMIN");
    private String name;
    private Role(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public static Role fromString(String name) {
        for (Role role : Role.values()) {
            if (role.name.equalsIgnoreCase(name)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No enum constant " + Role.class.getCanonicalName() + "." + name);
    }
}
