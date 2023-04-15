package br.com.inventory.mechanicalparts.entities.enums;

public enum EnumSexo {

    M(1, "MASCULINO"),
    F(2, "FEMININO"),
    O(3, "OUTROS");
    private int code;
    private String description;

    EnumSexo(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    public static EnumSexo toEnum(Integer code) {
        if(code == null) {
            return null;
        }

        for(EnumSexo x : EnumSexo.values()) {
            if(code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id " + code);
    }
}
