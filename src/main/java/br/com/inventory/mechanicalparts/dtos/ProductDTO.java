package br.com.inventory.mechanicalparts.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Integer quantityTotal;

    private BigDecimal value;

    private String identifyCode;

    private Integer quantityUsed;

    private CategoryDTO category;

    private LocalDateTime registrationDate;

    private LocalDateTime updateDate;

}
