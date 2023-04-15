package br.com.inventory.mechanicalparts.dtos;

import br.com.inventory.mechanicalparts.entities.enums.EnumTypeFuel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class CarDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String model;

    private String color;

    private String licensePlate;

    private EnumTypeFuel fuel;

    private Date yearOfManufacture;

    @JsonIgnore
    private ClientDTO client;
}
