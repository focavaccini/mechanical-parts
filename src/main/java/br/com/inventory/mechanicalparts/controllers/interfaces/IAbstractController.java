package br.com.inventory.mechanicalparts.controllers.interfaces;

import java.util.List;

public interface IAbstractController extends IAbstractService {

    <S, D> D convert(S source, Class<D> destClass);

    <E, D> List<E> convert (List<?> list, Class<D> destClass);
}
