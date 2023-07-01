package br.com.inventory.mechanicalparts.entities;

import br.com.inventory.mechanicalparts.controllers.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_product")
public class Product extends AbstractEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_product")
    private Long id;

    @Column(name = "name")
    private String name;

    @Transient
    private Integer quantityUsed;

    @Column(name = "totalQuantity")
    private Integer totalQuantity;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "identify_code", unique = true)
    private String identifyCode;

    @JsonIgnore
    @ManyToMany(mappedBy = "usedProducts", cascade = CascadeType.ALL)
    private List<ServicePerformed> servicePerformed;
//    @ManyToMany
//    @JoinTable(name="tb_product_service_performed", joinColumns=
//            {@JoinColumn(name="product_id")}, inverseJoinColumns=
//            {@JoinColumn(name="service_performe_id")})
//    private List<ServicePerformed> servicePerformed;
}
