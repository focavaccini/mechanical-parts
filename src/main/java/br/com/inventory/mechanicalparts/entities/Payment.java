package br.com.inventory.mechanicalparts.entities;

import br.com.inventory.mechanicalparts.controllers.AbstractEntity;
import br.com.inventory.mechanicalparts.entities.enums.EnumPaymentForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tb_payment")
public class Payment extends AbstractEntity<Long> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_payment")
    @SequenceGenerator(name = "id_payment", sequenceName = "payment_seq", allocationSize = 1)
    private Long id;

    @Column(name = "payment_form", nullable = false)
    private EnumPaymentForm paymentForm;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "total_value_paied")
    private BigDecimal totalValuePaied;

    @Column(name = "installments", nullable = false)
    private Integer installments;
}
