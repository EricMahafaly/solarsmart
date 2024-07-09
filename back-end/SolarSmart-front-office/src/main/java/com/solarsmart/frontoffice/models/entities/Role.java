package com.solarsmart.frontoffice.models.entities;

public enum Role {
    ADMIN("ADMIN"), CUSTOMER("CUSTOMER");
    private final String type;
    Role(String type){
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

}
