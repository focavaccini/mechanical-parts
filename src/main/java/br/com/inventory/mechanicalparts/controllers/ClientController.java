package br.com.inventory.mechanicalparts.controllers;

import br.com.inventory.mechanicalparts.controllers.interfaces.IClientController;
import br.com.inventory.mechanicalparts.dtos.ClientDTO;
import br.com.inventory.mechanicalparts.entities.Client;
import br.com.inventory.mechanicalparts.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ClientController extends AbstractController<ClientService> implements IClientController {

    private ClientService clientService;

    public ClientDTO insert(@RequestBody ClientDTO clientDTO){
        Client client =convert(clientDTO, Client.class);
        clientService.insert(client);
        return convert(client, ClientDTO.class);
    }

    public void update(@PathVariable Long idClient, @RequestBody ClientDTO clientDTO){
        Client client =convert(clientDTO, Client.class);
        clientService.update(idClient, client);
    }

    public ClientDTO buscarPorId(@PathVariable("id") Long idClient){
        return convert(clientService.buscarPorId(idClient), ClientDTO.class);
    }

}
