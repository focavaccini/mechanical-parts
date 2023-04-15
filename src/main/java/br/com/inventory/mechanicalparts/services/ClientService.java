package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.dtos.ClientDTO;
import br.com.inventory.mechanicalparts.entities.Address;
import br.com.inventory.mechanicalparts.entities.Client;

public interface ClientService extends IAbstractService<Client, Long> {

    Client insert(Client client);

    void update(Long idClient, Client client);

//   ClientDTO converterToDTO(Client client);
//
//    Client converterToEntity(ClientDTO clientDTO);

    Client buscarPorId(Long idClient);

    //void insertAddress(Address address);
}
