package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.entities.Address;
import br.com.inventory.mechanicalparts.entities.City;
import br.com.inventory.mechanicalparts.repositories.AddressRepository;
import br.com.inventory.mechanicalparts.services.AddressService;
import br.com.inventory.mechanicalparts.services.CityService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    private CityService cityService;

    @Override
    public Address insert(Address address) {

        City city = cityService.getById(address.getCity().getId());
        address.setCity(city);
        addressRepository.save(address);

        return address;
    }

    @Override
    public void update(Long idAddress, Address address) {
        Address addressManaged = getById(idAddress);

        addressManaged.setNeighborhood(Util.nvl(address.getNeighborhood(), addressManaged.getNeighborhood()));
        addressManaged.setCity(Util.nvl(cityService.getById(address.getCity().getId()), addressManaged.getCity()));
        addressManaged.setComplement(Util.nvl(address.getComplement(), addressManaged.getComplement()));
        addressManaged.setNumber(Util.nvl(address.getNumber(), addressManaged.getNumber()));
        addressManaged.setCep(Util.nvl(address.getCep(), addressManaged.getCep()));
        addressManaged.setStreet(Util.nvl(address.getStreet(), addressManaged.getStreet()));

        addressRepository.save(addressManaged);
    }

    @Override
    public Address getById(Long idAddress) {
        return addressRepository.findById(idAddress).get();
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public JpaRepository<Address, Long> getRepository() {
        return this.addressRepository;
    }
}
