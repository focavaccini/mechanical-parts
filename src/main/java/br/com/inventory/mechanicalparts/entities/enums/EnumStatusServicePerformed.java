package br.com.inventory.mechanicalparts.entities.enums;

public enum EnumStatusServicePerformed {

    EM_DIA("EM DIA", "EM DIA"),
    ATRASADO("ATRASADO", "ATRASADO"),

    FINALIZADO("FINALIZADO", "FINALIZDO"),

    ENTREGUE("ENTREGUE", "ENTREGUE");

    private String code;
    private String description;

    EnumStatusServicePerformed(String code, String description) {
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
