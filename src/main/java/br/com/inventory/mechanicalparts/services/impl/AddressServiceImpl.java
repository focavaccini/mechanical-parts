package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.entities.Address;
import br.com.inventory.mechanicalparts.repositories.AddressRepository;
import br.com.inventory.mechanicalparts.repositories.CityRepository;
import br.com.inventory.mechanicalparts.repositories.ClientRepository;
import br.com.inventory.mechanicalparts.services.AddressService;
import br.com.inventory.mechanicalparts.services.CityService;
import br.com.inventory.mechanicalparts.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private  AddressRepository addressRepository;

    private CityService cityService;

    private CityRepository cityRepository;

    private ClientService clientService;

    private ClientRepository clientRepository;

    @Override
    public Address insert(Address address) {

        addressRepository.save(address);

        //clientService.insertAddress(address);

        return address;
    }

    @Override
    public void update(Long idAddress, Address address) {
        Address addressManaged = addressRepository.findById(idAddress).orElseThrow();

        addressManaged.setNeighborhood(Util.nvl(address.getNeighborhood(), addressManaged.getNeighborhood()));
        addressManaged.setCity(Util.nvl(cityRepository.findById(address.getCity().getId()).get(), addressManaged.getCity()));
        addressManaged.setComplement(Util.nvl(address.getComplement(), addressManaged.getComplement()));
        addressManaged.setNumber(Util.nvl(address.getNumber(), addressManaged.getNumber()));
        addressManaged.setCep(Util.nvl(address.getCep(), addressManaged.getCep()));
        addressManaged.setStreet(Util.nvl(address.getStreet(), addressManaged.getStreet()));
        addressManaged.setClient(Util.nvl(clientRepository.findById(address.getClient().getId()).get(), addressManaged.getClient()));

        addressRepository.save(addressManaged);
    }

    @Override
    public JpaRepository<Address, Long> getRepository() {
        return this.addressRepository;
    }
}
