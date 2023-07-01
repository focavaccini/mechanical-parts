package br.com.inventory.mechanicalparts.dtos;

import br.com.inventory.mechanicalparts.entities.ServicePerformed;
import br.com.inventory.mechanicalparts.entities.UserLogin;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ProfessionalDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    @Transient
    private String phone;

    @Email(message = "Por favor, informe um e-mail válido.")
    private String email;

    @JsonIgnore
    private List<ServicePerformed> servicePerformed;

//    @JsonIgnore
//    private String login;
//
    @Transient
    private String password;

//    @JsonIgnoreProperties({"id","password","username", "authorities.authority"})
    private UserLogin user;

}
