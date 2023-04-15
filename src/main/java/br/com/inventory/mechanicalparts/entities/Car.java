package br.com.inventory.mechanicalparts.entities;

import br.com.inventory.mechanicalparts.controllers.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.inventory.mechanicalparts.entities.enums.EnumTypeFuel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "tb_car")
public class Car extends AbstractEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_car")
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "license_plate", unique = true)
    private String licensePlate;

    @Column(name = "type_of_fuel")
    private EnumTypeFuel fuel;

    @Column(name = "year_of_manufacture")
    private Date yearOfManufacture;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
