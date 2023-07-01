package br.com.inventory.mechanicalparts.entities.enums;

public enum EnumStatusServicePerformed {

    EM_DIA(1, "EM DIA"),
    ATRASADO(2, "ATRASADO");

    private int code;
    private String description;

    EnumStatusServicePerformed(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    public static EnumStatusServicePerformed toEnum(Integer code) {
        if(code == null) {
            return null;
        }

        for(EnumStatusServicePerformed x : EnumStatusServicePerformed.values()) {
            if(code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id " + code);
    }
}
