package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.entities.City;
import br.com.inventory.mechanicalparts.exceptions.ObjectNotFound;
import br.com.inventory.mechanicalparts.repositories.CityRepository;
import br.com.inventory.mechanicalparts.repositories.StateRepository;
import br.com.inventory.mechanicalparts.services.CityService;
import br.com.inventory.mechanicalparts.services.StateService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    private StateService stateService;

    private StateRepository stateRepository;

    @Override
    public City insert(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void update(Long idCity, City city) {
        City cityManaged = cityRepository.findById(idCity).orElseThrow();
        cityManaged.setName(Util.nvl(city.getName(), cityManaged.getName()));
        cityManaged.setState(Util.nvl(stateRepository.findById(city.getId()).get(), cityManaged.getState()));

        cityRepository.save(cityManaged);
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public City getById(Long idCity) {
        Optional<City> city = cityRepository.findById(idCity);
        return city.orElseThrow(()-> new ObjectNotFound("Object not found! Id " + idCity + ", Type: " + City.class.getName()));
    }

    @Override
    public JpaRepository<City, Long> getRepository() {
        return this.cityRepository;
    }
}
