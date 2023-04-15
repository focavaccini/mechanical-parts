package br.com.inventory.mechanicalparts.dtos;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String sigla;

}
