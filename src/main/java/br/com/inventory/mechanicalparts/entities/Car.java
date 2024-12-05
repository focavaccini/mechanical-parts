package br.com.inventory.mechanicalparts.entities;

import br.com.inventory.mechanicalparts.controllers.AbstractEntity;
import br.com.inventory.mechanicalparts.entities.enums.EnumTypeFuel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tb_car")
public class Car extends AbstractEntity<Long> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_car")
    @SequenceGenerator(name = "id_car", sequenceName = "car_seq", allocationSize = 1)
    private Long id;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "license_plate", unique = true, nullable = false)
    private String licensePlate;

    @Column(name = "type_of_fuel", nullable = false)
    private EnumTypeFuel fuel;

    @Column(name = "year_of_manufacture", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate yearOfManufacture;

    @JsonIgnore
    @OneToOne
    private Client client;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

}
