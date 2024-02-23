package br.com.inventory.mechanicalparts.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class AddressDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private CityDTO city;

    private String neighborhood;

    private String complement;

    private String number;

    private String street;

    private String cep;

    @JsonIgnore
    private ClientDTO client;
}
