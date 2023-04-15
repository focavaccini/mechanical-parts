package br.com.inventory.mechanicalparts.entities;

import br.com.inventory.mechanicalparts.controllers.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tb_city")
public class City extends AbstractEntity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_city")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_state")
    private State state;
}
