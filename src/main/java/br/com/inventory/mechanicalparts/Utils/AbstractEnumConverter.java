//package br.com.inventory.mechanicalparts.Util;
//
//package com.smartbr.clinica.pattern.enums.converter;
//
//import com.smartbr.clinica.pattern.enums.IEnum;
//
//import javax.persistence.AttributeConverter;
//import java.lang.reflect.ParameterizedType;
//
//public abstract class  AbstractEnumConverter<T extends Enum<T> & IEnum<E>, E> implements AttributeConverter<T, E> {
//
//    private final Class<T> clazz;
//
//    public AbstractEnumConverter() {
//        this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//    }
//
//    @Override
//    public E convertToDatabaseColumn(T attribute) {
//        return attribute != null ? attribute.getKey() : null;
//    }
//
//    @Override
//    public T convertToEntityAttribute(E dbData) {
//        T[] enums = clazz.getEnumConstants();
//
//        for (T e : enums) {
//            if (e.getKey().equals(dbData)) {
//                return e;
//            }
//        }
//
//        return null;
//    }
//
//}