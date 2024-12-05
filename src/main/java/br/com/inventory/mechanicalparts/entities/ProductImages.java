package br.com.inventory.mechanicalparts.entities;

import br.com.inventory.mechanicalparts.controllers.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tb_product_images")
public class ProductImages extends AbstractEntity<Long> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_product_images")
    @SequenceGenerator(name = "id_product_images", sequenceName = "product_images_seq", allocationSize = 1)
    private Long id;

    @Column(name = "path", nullable = false)
    private String path;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
