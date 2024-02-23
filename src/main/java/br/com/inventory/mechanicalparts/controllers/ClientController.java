package br.com.inventory.mechanicalparts.controllers;

import br.com.inventory.mechanicalparts.controllers.interfaces.IClientController;
import br.com.inventory.mechanicalparts.dtos.CarDTO;
import br.com.inventory.mechanicalparts.dtos.ClientDTO;
import br.com.inventory.mechanicalparts.entities.Car;
import br.com.inventory.mechanicalparts.entities.Client;
import br.com.inventory.mechanicalparts.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientController extends AbstractController<ClientService> implements IClientController {

    private ClientService clientService;

    public ClientDTO insert(ClientDTO clientDTO){
        Client client = convert(clientDTO, Client.class);
        return convert(clientService.insert(client), ClientDTO.class);
    }

    public void update(Long idClient, ClientDTO clientDTO){
        Client client = convert(clientDTO, Client.class);
        clientService.update(idClient, client);
    }

    public ClientDTO getById(Long idClient){
        return convert(clientService.getById(idClient), ClientDTO.class);
    }

    @Override
    public List<ClientDTO> getAll() {
        return convert(clientService.getAll(), ClientDTO.class);
    }

    @Override
    public CarDTO insertCar(Long idClient, CarDTO carDTO) {
        return convert(clientService.insertCar(idClient, convert(carDTO, Car.class)), CarDTO.class);
    }

    @Override
    public void updateCar(Long idClient, Long idCar,CarDTO carDTO) {
        clientService.updateCar(idClient, idCar, convert(carDTO, Car.class));
    }
}
