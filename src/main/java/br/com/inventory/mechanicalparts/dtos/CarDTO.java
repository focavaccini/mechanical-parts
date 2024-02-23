package br.com.inventory.mechanicalparts.dtos;

import br.com.inventory.mechanicalparts.entities.enums.EnumTypeFuel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class CarDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String model;

    private String color;

    private String licensePlate;

    private EnumTypeFuel fuel;

    private LocalDate yearOfManufacture;

    @JsonIgnore
    private ClientDTO client;

    private LocalDateTime registrationDate;

    private LocalDateTime updateDate;
}
