package br.com.inventory.mechanicalparts.entities.enums;

public enum EnumRole {
    ROLE_ADMIN("ROLE_ADMIN", "ROLE_ADMIN"), ROLE_USER("ROLE_USER", "ROLE_USER");

    private String code;
    private String description;

    EnumRole(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
