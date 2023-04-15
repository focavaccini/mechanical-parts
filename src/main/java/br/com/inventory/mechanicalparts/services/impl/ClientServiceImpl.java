package br.com.inventory.mechanicalparts.services.impl;

//import br.com.inventory.mechanicalparts.Util.Utils;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.Utils.ValidateCPF;
import br.com.inventory.mechanicalparts.entities.Client;
import br.com.inventory.mechanicalparts.repositories.ClientRepository;
import br.com.inventory.mechanicalparts.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;

    @Override
    public Client insert(Client client) {
        onPrepareInsertOrUpdate(client);
        return clientRepository.save(client);
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

    private void onPrepareInsertOrUpdate(Client client) {
        checkIfCpfAlreadyExists(client);
        //checkIfTheCarAlreadyBelongsToSomeoneElse(client);
        checkIfPhoneAlreadyExists(client);
        checkIfEmailAlreadyExists(client);
    }

    private void checkIfEmailAlreadyExists(Client client){
        Client clientManaged = clientRepository.findByEmail(client.getEmail());
        if(clientManaged != null && !clientManaged.equals(client)){
            System.out.println("E-mail já pertence a outra pessoa");
        }
    }
    private void checkIfPhoneAlreadyExists(Client client){
        Client clientManaged = clientRepository.findByPhone(client.getPhone());
        if(clientManaged != null && !clientManaged.equals(client)){
            System.out.println("Telefone já pertence a outra pessoa");
        }
    }
    private void checkIfCpfAlreadyExists(Client client){
        ValidateCPF.isCPF(client.getCpf());
        Client clientManaged = clientRepository.findByCpf(client.getCpf());
        if(clientManaged != null && !clientManaged.equals(client)){
            System.out.println("Cpf já pertence a outra pessoa");
        }
    }

    @Override
    public Client buscarPorId(final Long idClient) {
        return clientRepository.findById(idClient).orElse(null);
    }

    @Override
    public JpaRepository<Client, Long> getRepository() {
        return this.clientRepository;
    }
}
