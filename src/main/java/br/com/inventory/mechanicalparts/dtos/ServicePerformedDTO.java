package br.com.inventory.mechanicalparts.dtos;

import br.com.inventory.mechanicalparts.entities.Professional;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ServicePerformedDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private BigDecimal value;

    private String description;

    private String observation;

    private String problemReported;

    private Integer serviceDays;

    private Date deliveryDate;

    private List<ProductDTO> usedProducts;

    private BigDecimal laborCost;

    private BigDecimal totalValue;

    private Professional professional;

    private CarDTO car;

    private Integer daysForDelivery;

    public void setDaysForDelivery() {
        this.daysForDelivery = deliveryDate.compareTo(Date.from(Instant.now()));
    }
}
