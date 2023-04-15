package br.com.inventory.mechanicalparts.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Integer quantityUsed;

    private Integer quantityTotal;

    private BigDecimal value;

    private String identifyCode;
}
