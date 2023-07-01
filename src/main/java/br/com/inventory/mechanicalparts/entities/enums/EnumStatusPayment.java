package br.com.inventory.mechanicalparts.entities.enums;

public enum EnumStatusPayment {

    PENDENTE(1, "PENDENTE"),
    PAGO(2, "PAGO"),
    ATRASADO(3, "ATRASADO");
    private int code;
    private String description;

    EnumStatusPayment(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
    public static EnumStatusPayment toEnum(Integer code) {
        if(code == null) {
            return null;
        }

        for(EnumStatusPayment x : EnumStatusPayment.values()) {
            if(code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid id " + code);
    }
}
