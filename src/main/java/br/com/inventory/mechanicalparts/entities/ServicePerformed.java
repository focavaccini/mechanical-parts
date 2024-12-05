package br.com.inventory.mechanicalparts.entities;

import br.com.inventory.mechanicalparts.controllers.AbstractEntity;
import br.com.inventory.mechanicalparts.entities.enums.EnumStatusPayment;
import br.com.inventory.mechanicalparts.entities.enums.EnumStatusServicePerformed;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_service_performed")
public class ServicePerformed extends AbstractEntity<Long> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_service_performed")
    @SequenceGenerator(name = "id_service_performed", sequenceName = "service_performe_seq", allocationSize = 1)
    private Long id;

//    @Column(name = "value")
//    private BigDecimal value;

    @Column(name = "description")
    private String description;

    @Column(name = "observation")
    private String observation;

    @Column(name = "problemReported")
    private String problemReported;

    @Column(name = "service_days")
    private Integer serviceDays;

    @Column(name = "delivery_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate deliveryDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="tb_service_performed_product",
            joinColumns= {@JoinColumn(name="service_performe_id")},
            inverseJoinColumns= {@JoinColumn(name="product_id")})
    private List<Product> usedProducts;

    @Column(name = "labor_cost")
    private BigDecimal laborCost;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;

    @Column(name = "status_service")
    private EnumStatusServicePerformed status;

    @OneToOne
    private Car car;

    @Column(name = "status_payment")
    private EnumStatusPayment statusPayment;

    private Integer daysForDelivery;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToOne
    private Payment payment;

//    @PreUpdate
//    private void preUpdate() {
//        int days = this.deliveryDate.compareTo(LocalDate.now());
//        if(days < 0){
//            this.status = EnumStatusServicePerformed.ATRASADO;
//        }else{
//            this.status = EnumStatusServicePerformed.EM_DIA;
//        }
//    }
//
//    @PrePersist
//    private void prePersist() {
//        int days = this.deliveryDate.compareTo(LocalDate.now());
//        if(days < 0){
//            this.status = EnumStatusServicePerformed.ATRASADO;
//        }else{
//            this.status = EnumStatusServicePerformed.EM_DIA;
//        }
//    }

//    public void setDaysForDelivery() {
//        this.daysForDelivery = deliveryDate.compareTo(LocalDate.from(Instant.now()));
//    }
}
