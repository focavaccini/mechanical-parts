package br.com.inventory.mechanicalparts.dtos;

import br.com.inventory.mechanicalparts.entities.enums.EnumSexo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String phone;

    @Email(message = "Por favor, informe um e-mail válido.")
    private String email;

    private String cpf;

    private EnumSexo sexo;

    private LocalDate birthdate;
}
