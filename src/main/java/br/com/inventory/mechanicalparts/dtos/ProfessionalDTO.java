package br.com.inventory.mechanicalparts.dtos;

import br.com.inventory.mechanicalparts.entities.ServicePerformed;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProfessionalDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    @Transient
    private String phone;

    @Email(message = "Por favor, informe um e-mail válido.")
    private String email;

    private String code;
    @JsonIgnore
    private List<ServicePerformed> servicePerformed;

    private LocalDateTime registrationDate;

    private LocalDateTime updateDate;
}
