package br.com.inventory.mechanicalparts.Utils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.smartbr.clinica.pattern.enums;

/**
 * @param <E>
 * @author Edimar Fernando Liberali
 */
public interface IEnum<E> {

    E getKey();

    String getValue();

    default String getName() {
        return ((Enum) this).name();
    }

}