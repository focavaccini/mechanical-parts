package br.com.inventory.mechanicalparts.controllers;

import java.io.Serializable;

public class AbstractEntity<I extends Number> implements Serializable {
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
