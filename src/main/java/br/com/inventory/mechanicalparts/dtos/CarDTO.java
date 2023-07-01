package br.com.inventory.mechanicalparts.dtos;

import br.com.inventory.mechanicalparts.entities.enums.EnumTypeFuel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class CarDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String model;

    private String color;

    private String licensePlate;

    private EnumTypeFuel fuel;

    private LocalDate yearOfManufacture;

    private ClientDTO client;
}
