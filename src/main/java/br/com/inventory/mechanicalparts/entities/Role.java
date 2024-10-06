package br.com.inventory.mechanicalparts.entities;

import br.com.inventory.mechanicalparts.entities.enums.EnumRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "tb_role")
public class Role extends AbstractEntity<Long> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_role")
    @SequenceGenerator(name = "id_role", sequenceName = "role_seq", allocationSize = 1)
    private Long id;

    private String name;

    private EnumRole role;

//    @ManyToMany(mappedBy = "role")
//    private Collection<User> users;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    public Role(String name) {
        this.name = name;
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role() {
    }
}