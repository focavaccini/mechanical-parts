package br.com.inventory.mechanicalparts.dtos;

import br.com.inventory.mechanicalparts.entities.Professional;
import br.com.inventory.mechanicalparts.entities.enums.EnumStatusServicePerformed;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ServicePerformedDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String description;

    private String observation;

    private String problemReported;

    private Integer serviceDays;

    private LocalDate deliveryDate;

    private List<ProductDTO> usedProducts;

    private BigDecimal laborCost;

    private BigDecimal totalValue;

    private Professional professional;

    private CarDTO car;

    private Integer daysForDelivery;

    private EnumStatusServicePerformed status;

    @PreUpdate
    private void preUpdate() {
        int days = this.deliveryDate.compareTo(LocalDate.now());
        if(days < 0){
            this.status = EnumStatusServicePerformed.ATRASADO;
        }else{
            this.status = EnumStatusServicePerformed.EM_DIA;
        }
    }

    @PrePersist
    private void prePersist() {
        int days = this.deliveryDate.compareTo(LocalDate.now());
        if(days < 0){
            this.status = EnumStatusServicePerformed.ATRASADO;
        }else{
            this.status = EnumStatusServicePerformed.EM_DIA;
        }
    }
}
