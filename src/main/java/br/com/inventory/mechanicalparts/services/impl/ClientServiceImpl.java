package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.Utils.ValidateCPF;
import br.com.inventory.mechanicalparts.entities.Address;
import br.com.inventory.mechanicalparts.entities.Car;
import br.com.inventory.mechanicalparts.entities.Client;
import br.com.inventory.mechanicalparts.entities.User;
import br.com.inventory.mechanicalparts.exceptions.BadRequestException;
import br.com.inventory.mechanicalparts.exceptions.ObjectNotFound;
import br.com.inventory.mechanicalparts.repositories.ClientRepository;
import br.com.inventory.mechanicalparts.services.AddressService;
import br.com.inventory.mechanicalparts.services.CarService;
import br.com.inventory.mechanicalparts.services.ClientService;
import br.com.inventory.mechanicalparts.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    ClientRepository clientRepository;

    private UserService userService;

    private EmailService emailService;

    private AddressService addressService;

    private CarService carService;

    @Override
    @Transactional
    public Client insert(Client client) {
        Client newClient;
        onPrepareInsertOrUpdate(client);
        User user = userService.saveUser(client.getEmail());
        client.setActive(true);
        client.setUser(user);
        client.setRegistrationDate(LocalDateTime.now());
        newClient = clientRepository.save(client);

        try {
            emailService.sendMailToClient("Olá " + client.getName(), client.getEmail(), emailService.getContentMail(newClient.getName(), user.getPassword(), "https://stackoverflow.com/questions/52483260/validate-phone-number-with-yup"));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        Address address = newClient.getAddress();
        address.setClient(newClient);
        addressService.insert(address);

        Car car = newClient.getCar();
        car.setClient(newClient);
        carService.insert(car);

        return newClient;
    }

    @Override
    public void update(Long idClient, Client client) {
        Client clientManaged = getById(idClient);

        clientManaged.setUpdateDate(LocalDateTime.now());
        clientManaged.setEmail(Util.nvl(client.getEmail(), clientManaged.getEmail()));
        clientManaged.setName(Util.nvl(client.getName(), clientManaged.getName()));
        clientManaged.setBirthdate(Util.nvl(client.getBirthdate(), clientManaged.getBirthdate()));
        clientManaged.setPhone(Util.nvl(client.getPhone(), clientManaged.getPhone()));
        clientManaged.setCpf(Util.nvl(client.getCpf(), clientManaged.getCpf()));
        clientManaged.setActive(Util.nvl(client.getActive(), clientManaged.getActive()));
        carService.update(clientManaged.getCar().getId(), client.getCar());
        addressService.update(clientManaged.getAddress().getId(), client.getAddress());

        onPrepareInsertOrUpdate(clientManaged);
        clientRepository.save(clientManaged);
    }

    public List<Address> insertAddress(Address address) {
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        return addresses;
    }

    @Override
    public Car insertCar(Long idClient, Car car) {
        Client client = getById(idClient);
        car.setClient(client);
        return carService.insert(car);
    }

    @Override
    public void updateCar(Long idClient, Long idCar, Car car) {
        carService.update(idCar, car);
    }

    private void onPrepareInsertOrUpdate(Client client) {
        checkIfCpfAlreadyExists(client);
        checkIfPhoneAlreadyExists(client);
        checkIfEmailAlreadyExists(client);
    }

    private void checkIfEmailAlreadyExists(Client client) {
        Client clientManaged = clientRepository.findByEmail(client.getEmail());
        if (clientManaged != null && !clientManaged.getId().equals(client.getId())) {
            throw new BadRequestException("Email já pertence a outra pessoa");
        }
    }

    private void checkIfPhoneAlreadyExists(Client client) {
        Client clientManaged = clientRepository.findByPhone(client.getPhone());
        if (clientManaged != null && !clientManaged.getId().equals(client.getId())) {
            throw new BadRequestException("Telefone já pertence a outra pessoa");
        }
    }

    private void checkIfCpfAlreadyExists(Client client) {
        ValidateCPF.isCPF(client.getCpf());
        Client clientManaged = clientRepository.findByCpf(client.getCpf());
        if (clientManaged != null && !clientManaged.getId().equals(client.getId())) {
            throw new BadRequestException("Cpf já pertence a outra pessoa");
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
