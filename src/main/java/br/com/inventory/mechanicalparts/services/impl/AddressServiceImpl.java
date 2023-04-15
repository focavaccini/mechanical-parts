package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.dtos.AddressDTO;
import br.com.inventory.mechanicalparts.entities.Address;
import br.com.inventory.mechanicalparts.entities.City;
import br.com.inventory.mechanicalparts.entities.Client;
import br.com.inventory.mechanicalparts.repositories.AddressRepository;
import br.com.inventory.mechanicalparts.repositories.CityRepository;
import br.com.inventory.mechanicalparts.repositories.ClientRepository;
import br.com.inventory.mechanicalparts.services.AddressService;
import br.com.inventory.mechanicalparts.services.CityService;
import br.com.inventory.mechanicalparts.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @Override
//    public AddressDTO convertToDTO(Address address) {
//        AddressDTO addressDTO = new AddressDTO();
//        addressDTO.setId(address.getId());
//        addressDTO.setStreet(address.getStreet());
//        addressDTO.setNumber(address.getNumber());
//        addressDTO.setNeighborhood(address.getNeighborhood());
//        addressDTO.setComplement(address.getComplement());
//        addressDTO.setCity(cityService.converterToDTO(address.getCity()));
//        addressDTO.setClient(clientService.converterToDTO(address.getClient()));
//        return addressDTO;
//    }
//
//    @Override
//    public Address convertToEntity(AddressDTO addressDTO) {
//        Address address = new Address();
//        address.setId(addressDTO.getId());
//        address.setCep(addressDTO.getCep());
//        address.setNeighborhood(addressDTO.getNeighborhood());
//        address.setComplement(address.getComplement());
//        address.setNumber(addressDTO.getNumber());
//        address.setStreet(addressDTO.getStreet());
//
//        City city = cityRepository.findById(addressDTO.getCity().getId()).get();
//        Client client = clientRepository.findById(addressDTO.getClient().getId()).get();
//        address.setCity(city);
//        address.setClient(client);
//        return address;
//    }

    @Override
    public JpaRepository<Address, Long> getRepository() {
        return this.addressRepository;
    }
}
