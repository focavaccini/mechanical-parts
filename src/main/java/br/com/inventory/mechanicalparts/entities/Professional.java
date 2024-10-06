package br.com.inventory.mechanicalparts.entities;

import br.com.inventory.mechanicalparts.controllers.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_professional")
public class Professional extends AbstractEntity<Long>  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_professional")
    @SequenceGenerator(name = "id_professional", sequenceName = "professional_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "code", unique = true)
    private String code;

    @JsonIgnore
    @OneToMany(mappedBy = "professional")
    private List<ServicePerformed> servicePerformed;

    @OneToOne
    @JsonIgnore
    private User user;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

}
