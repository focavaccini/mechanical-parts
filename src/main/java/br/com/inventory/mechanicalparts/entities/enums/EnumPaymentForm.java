package br.com.inventory.mechanicalparts.entities.enums;

public enum EnumPaymentForm {

    DINHEIRO("DINHEIRO", "DINHEIRO"),
    CARTAO("CARTAO", "CARTAO"),
    PIX("PIX", "PIX"),
    CHEQUE("CHEQUE", "CHEQUE");
    private String code;
    private String description;

    EnumPaymentForm(String code, String description) {
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
