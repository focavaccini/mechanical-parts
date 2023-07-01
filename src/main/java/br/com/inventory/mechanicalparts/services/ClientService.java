package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.entities.Address;
import br.com.inventory.mechanicalparts.entities.Client;

import java.util.List;

public interface ClientService extends IAbstractService<Client, Long> {

    Client insert(Client client);

    void update(Long idClient, Client client);

    Client getById(Long idClient);

    List<Client> getAll();

    List<Address> insertAddress(Address address);
}
