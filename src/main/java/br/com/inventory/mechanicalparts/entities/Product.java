package br.com.inventory.mechanicalparts.entities;

import br.com.inventory.mechanicalparts.controllers.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_product")
public class Product extends AbstractEntity<Long> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_product")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private Boolean active;

    @Transient
    private Integer quantityUsed = 0;

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

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImages> images;
}
