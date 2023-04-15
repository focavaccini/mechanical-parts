package br.com.inventory.mechanicalparts.entities.enums;

public enum EnumTypeFuel {

    GASOLINA(1, "GASOLINA"),
    ALCOOL(2, "ALCOOL"),
    DIESEL(3, "DIESEL"),
    ELETRICO(4, "ELÉTRICO");

    private int code;
    private String description;

    EnumTypeFuel(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    public static EnumTypeFuel toEnum(Integer code) {
        if(code == null) {
            return null;
        }

        for(EnumTypeFuel x : EnumTypeFuel.values()) {
            if(code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id " + code);
    }
}
