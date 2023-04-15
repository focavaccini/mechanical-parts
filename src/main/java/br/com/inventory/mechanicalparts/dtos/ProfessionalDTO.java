package br.com.inventory.mechanicalparts.dtos;

import br.com.inventory.mechanicalparts.entities.ServicePerformed;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ProfessionalDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String phone;

    @Email(message = "Por favor, informe um e-mail válido.")
    private String email;

    private List<ServicePerformed> servicePerformed;
}
