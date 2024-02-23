package br.com.inventory.mechanicalparts.dtos;

import br.com.inventory.mechanicalparts.entities.enums.EnumSexo;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ClientDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String phone;

    @Email(message = "Por favor, informe um e-mail válido.")
    private String email;

    private String cpf;

    private EnumSexo sexo;

    private LocalDate birthdate;

    private AddressDTO address;

    private CarDTO car;

    private LocalDateTime registrationDate;

    private LocalDateTime updateDate;
}
