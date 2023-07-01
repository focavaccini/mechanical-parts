package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.Utils.ValidateCPF;
import br.com.inventory.mechanicalparts.entities.Address;
import br.com.inventory.mechanicalparts.entities.Client;
import br.com.inventory.mechanicalparts.exceptions.BadRequestException;
import br.com.inventory.mechanicalparts.exceptions.ObjectNotFound;
import br.com.inventory.mechanicalparts.repositories.ClientRepository;
import br.com.inventory.mechanicalparts.services.ClientService;
import br.com.inventory.mechanicalparts.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;

    private UserService userService;

    @Override
    public Client insert(Client client) {
        Client newClient;
        onPrepareInsertOrUpdate(client);
        newClient = clientRepository.save(client);

        try {
            userService.saveUser(client.getUser());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return newClient;
    }

    @Override
    public void update(Long idClient, Client client) {
        Client clientManaged = clientRepository.findById(idClient).orElseThrow();

        clientManaged.setEmail(Util.nvl(client.getEmail(), clientManaged.getEmail()));
        clientManaged.setName(Util.nvl(client.getName(), clientManaged.getName()));
        clientManaged.setBirthdate(Util.nvl(client.getBirthdate(), clientManaged.getBirthdate()));
        clientManaged.setPhone(Util.nvl(client.getPhone(), clientManaged.getPhone()));
        clientManaged.setCpf(Util.nvl(client.getCpf(), clientManaged.getCpf()));

        onPrepareInsertOrUpdate(client);
        clientRepository.save(clientManaged);
    }

    public List<Address> insertAddress(Address address){
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        return  addresses;
    }

    private void onPrepareInsertOrUpdate(Client client) {
        checkIfCpfAlreadyExists(client);
        //checkIfTheCarAlreadyBelongsToSomeoneElse(client);
        checkIfPhoneAlreadyExists(client);
        checkIfEmailAlreadyExists(client);
    }

    private void checkIfEmailAlreadyExists(Client client){
        Client clientManaged = clientRepository.findByEmail(client.getEmail());
        if(clientManaged != null && !clientManaged.equals(client)){
            throw new BadRequestException("Email já pertence a outra pessoa" + client.getId());
        }
    }
    private void checkIfPhoneAlreadyExists(Client client){
        Client clientManaged = clientRepository.findByPhone(client.getPhone());
        if(clientManaged != null && !clientManaged.equals(client)){
            throw new BadRequestException("Telefone já pertence a outra pessoa" + client.getId());
        }
    }
    private void checkIfCpfAlreadyExists(Client client){
        ValidateCPF.isCPF(client.getCpf());
        Client clientManaged = clientRepository.findByCpf(client.getCpf());
        if(clientManaged != null && !clientManaged.equals(client)){
            throw new BadRequestException("Cpf já pertence a outra pessoa" + client.getId());
        }
    }

    @Override
    public Client getById(final Long idClient) {
        Optional<Client> client = clientRepository.findById(idClient);
        return client.orElseThrow(() -> new ObjectNotFound("Object not found! Id " + idClient + ", Type: " + Client.class.getName()));
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public JpaRepository<Client, Long> getRepository() {
        return this.clientRepository;
    }
}
