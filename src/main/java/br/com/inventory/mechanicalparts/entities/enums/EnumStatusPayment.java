package br.com.inventory.mechanicalparts.entities.enums;

public enum EnumStatusPayment {

    PENDENTE("PENDENTE", "PENDENTE"),
    PAGO("PAGO", "PAGO"),
    ATRASADO("ATRASADO", "ATRASADO");
    private String code;
    private String description;

    EnumStatusPayment(String code, String description) {
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
