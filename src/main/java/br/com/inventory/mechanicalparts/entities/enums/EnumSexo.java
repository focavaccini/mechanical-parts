package br.com.inventory.mechanicalparts.entities.enums;

public enum EnumSexo {

    MASCULINO("MASCULINO", "MASCULINO"),
    FEMININO("FEMININO", "FEMININO"),
    OUTROS("OUTROS", "OUTROS");
    private String code;
    private String description;

    EnumSexo(String code, String description) {
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
