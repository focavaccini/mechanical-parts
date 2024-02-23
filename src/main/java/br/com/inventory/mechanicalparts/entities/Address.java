package br.com.inventory.mechanicalparts.entities;

import br.com.inventory.mechanicalparts.controllers.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tb_address")
public class Address extends AbstractEntity<Long> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_addres")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "number")
    private String number;

    @Column(name = "street")
    private String street;

    @Column(name = "complement")
    private String complement;

    @Column(name = "cep")
    private String cep;

    @OneToOne
    @JsonIgnore
    private Client client;

    @Column(name = "data_registro")
    private LocalDateTime dateRegistro;
}
