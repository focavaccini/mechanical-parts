package br.com.inventory.mechanicalparts.entities;

import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractEntity<I extends Number> implements Serializable {
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
