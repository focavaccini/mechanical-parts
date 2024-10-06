package br.com.inventory.mechanicalparts.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "tb_privelege")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_privelege")
    @SequenceGenerator(name = "id_privelege", sequenceName = "privelege_seq", allocationSize = 1)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public Privilege(String name) {
    }

    public Privilege() {

    }
}
