package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.entities.City;

public interface CityService extends IAbstractService<City, Long> {

    City insert(City city);

    void update(Long idCity, City city);
}
