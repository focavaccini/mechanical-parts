package br.com.inventory.mechanicalparts.services.impl;

//import br.com.inventory.mechanicalparts.Util.Utils;
import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.Utils.ValidateCPF;
import br.com.inventory.mechanicalparts.dtos.ClientDTO;
import br.com.inventory.mechanicalparts.entities.Address;
import br.com.inventory.mechanicalparts.entities.Client;
import br.com.inventory.mechanicalparts.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import br.com.inventory.mechanicalparts.repositories.ClientRepository;

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

//    private void checkIfTheCarAlreadyBelongsToSomeoneElse(Client client){
//        Client clientManaged = clientRepository.findByEmail(client.getEmail());
//        if(clientManaged != null && !clientManaged.equals(client)){
//            System.out.println("E-mail já pertence a outra pessoa");
//        }
//    }


//    @Override
//    public void insertAddress(Address address) {
//        Client client = clientRepository.findById(address.getClient().getId()).get();
//        client.getAddresses().add(address);
//    }

//    @Override
//    public ClientDTO converterToDTO(Client client) {
//        ClientDTO clientDTO = new ClientDTO();
//        clientDTO.setId(client.getId());
//        clientDTO.setName(client.getName());
//        clientDTO.setCpf(client.getCpf());
//        clientDTO.setEmail(client.getEmail());
//        clientDTO.setBirthdate(client.getBirthdate());
//        clientDTO.setPhone(client.getPhone());
//
//        return clientDTO;
//    }
//
//    @Override
//    public Client converterToEntity(ClientDTO clientDTO) {
//        Client client = new Client();
//        client.setId(clientDTO.getId());
//        client.setName(clientDTO.getName());
//        client.setCpf(clientDTO.getCpf());
//        client.setEmail(clientDTO.getEmail());
//        client.setBirthdate(clientDTO.getBirthdate());
//        client.setPhone(clientDTO.getPhone());
//        return client;
//    }

    @Override
    public Client buscarPorId(final Long idClient) {
        return clientRepository.findById(idClient).orElse(null);
    }

    @Override
    public JpaRepository<Client, Long> getRepository() {
        return this.clientRepository;
    }
}
