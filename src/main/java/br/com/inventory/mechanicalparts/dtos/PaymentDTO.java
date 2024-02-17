package br.com.inventory.mechanicalparts.dtos;

import br.com.inventory.mechanicalparts.entities.enums.EnumPaymentForm;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private EnumPaymentForm paymentForm;

    private LocalDateTime paymentDate;

    private BigDecimal totalValuePaied;

    private Integer installments;
}
