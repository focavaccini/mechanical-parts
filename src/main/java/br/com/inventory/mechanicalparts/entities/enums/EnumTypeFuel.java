package br.com.inventory.mechanicalparts.entities.enums;

public enum EnumTypeFuel {

    GASOLINA("GASOLINA", "GASOLINA"),
    ALCOOL("ALCOOL", "ALCOOL"),
    DIESEL("DIESEL", "DIESEL"),
    ELETRICO("ELETRICO", "ELÉTRICO");

    private String code;
    private String description;

    EnumTypeFuel(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
//    public static EnumTypeFuel toEnum(Integer code) {
//        if(code == null) {
//            return null;
//        }
//
//        for(EnumTypeFuel x : EnumTypeFuel.values()) {
//            if(code.equals(x.getCode())) {
//                return x;
//            }
//        }
//
//        throw new IllegalArgumentException("Invalid id " + code);
//    }
}
