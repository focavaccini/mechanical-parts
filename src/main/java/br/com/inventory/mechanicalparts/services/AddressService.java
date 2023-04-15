package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.entities.Address;

public interface AddressService extends IAbstractService<Address, Long> {

    Address insert(Address address);

    void update(Long idAddress, Address address);

}
