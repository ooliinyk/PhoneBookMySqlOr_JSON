package com.entity;


public enum RoleTypes {
    USER("USER"),
    ADMIN("ADMIN"),
    ANONYM("ANONYM");

    String roleType;

    private RoleTypes(String roleType) {
        this.roleType = roleType;
    }

    public String getUserProfileType() {
        return roleType;
    }
}
