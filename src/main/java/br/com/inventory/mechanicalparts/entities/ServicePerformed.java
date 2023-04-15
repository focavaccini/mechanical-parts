package br.com.inventory.mechanicalparts.entities;

import br.com.inventory.mechanicalparts.controllers.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_service_performed")
public class ServicePerformed extends AbstractEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_service_performed")
    private Long id;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "description")
    private String description;

    @Column(name = "observation")
    private String observation;

    @Column(name = "problemReported")
    private String problemReported;

    @Column(name = "service_days")
    private Integer serviceDays;

    @Column(name = "delivery_date")
    private Date deliveryDate;

    @ManyToMany(mappedBy = "servicePerformed")
    private List<Product> usedProducts;

    @Column(name = "labor_cost")
    private BigDecimal laborCost;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;

    @OneToOne
    private Car car;
}
