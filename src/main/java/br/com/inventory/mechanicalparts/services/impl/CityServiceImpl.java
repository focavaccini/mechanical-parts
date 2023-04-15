package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.dtos.CityDTO;
import br.com.inventory.mechanicalparts.entities.City;
import br.com.inventory.mechanicalparts.entities.State;
import br.com.inventory.mechanicalparts.repositories.CityRepository;
import br.com.inventory.mechanicalparts.repositories.StateRepository;
import br.com.inventory.mechanicalparts.services.CityService;
import br.com.inventory.mechanicalparts.services.StateService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

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

//    @Override
//    public CityDTO converterToDTO(City city) {
//        CityDTO cityDTO = new CityDTO();
//        cityDTO.setId(city.getId());
//        cityDTO.setName(city.getName());
//        cityDTO.setState(stateService.converterToDTO(city.getState()));
//        return cityDTO;
//    }
//
//    @Override
//    public City converterToEntity(CityDTO cityDTO) {
//        City city = new City();
//        city.setId(cityDTO.getId());
//        city.setName(cityDTO.getName());
//
//        State state = stateRepository.findById(cityDTO.getState().getId()).get();
//        city.setState(state);
//         return city;
//    }

    @Override
    public JpaRepository<City, Long> getRepository() {
        return this.cityRepository;
    }
}
