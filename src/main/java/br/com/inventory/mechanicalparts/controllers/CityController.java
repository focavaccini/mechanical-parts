package br.com.inventory.mechanicalparts.controllers;

import br.com.inventory.mechanicalparts.controllers.interfaces.ICityController;
import br.com.inventory.mechanicalparts.dtos.CityDTO;
import br.com.inventory.mechanicalparts.entities.City;
import br.com.inventory.mechanicalparts.services.CityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CityController extends AbstractController<CityService> implements ICityController {

    private CityService cityService;

    public CityDTO insert(@RequestBody CityDTO cityDTO){
        City city = convert(cityDTO, City.class);
        cityService.insert(city);
        return convert(city, CityDTO.class);
    }

    public void update(@PathVariable Long idCity, @RequestBody CityDTO cityDTO){
        City city = convert(cityDTO, City.class);
        cityService.update(idCity, city);
    }
}
