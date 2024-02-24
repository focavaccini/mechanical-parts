package br.com.inventory.mechanicalparts.Utils;

public interface IEnum<E> {

    E getKey();

    String getValue();

    default String getName() {
        return ((Enum) this).name();
    }

}