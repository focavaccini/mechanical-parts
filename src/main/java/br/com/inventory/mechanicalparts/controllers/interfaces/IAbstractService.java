package br.com.inventory.mechanicalparts.controllers.interfaces;

import br.com.inventory.mechanicalparts.controllers.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAbstractService<E extends AbstractEntity<?>, I> {
    JpaRepository<E, I> getRepository();
}