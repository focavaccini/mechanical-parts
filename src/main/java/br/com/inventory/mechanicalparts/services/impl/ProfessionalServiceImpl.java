package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.entities.Professional;
import br.com.inventory.mechanicalparts.entities.User;
import br.com.inventory.mechanicalparts.exceptions.BadRequestException;
import br.com.inventory.mechanicalparts.exceptions.ObjectNotFound;
import br.com.inventory.mechanicalparts.repositories.ProfessionalRepository;
import br.com.inventory.mechanicalparts.services.ProfessionalService;
import br.com.inventory.mechanicalparts.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfessionalServiceImpl implements ProfessionalService {

    private ProfessionalRepository professionalRepository;

    private UserService userService;

    private EmailService emailService;

    @Override
    public Professional insert(Professional professional) {
        Professional newProfessional = new Professional();
        professional.setRegistrationDate(LocalDateTime.now());

        User user = userService.saveUser("prefessional", professional.getEmail());
        professional.setUser(user);
        professional.setActive(true);
        newProfessional = professionalRepository.save(professional);

        try {
            emailService.sendMailToClient("Olá " + professional.getName(), newProfessional.getEmail(), emailService.getContentMail(newProfessional.getName(), user.getPassword(), "http://localhost:3000/change-password"));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return newProfessional;
    }

    @Override
    public void update(Long idProfessional, Professional professional) {
        Professional professionalManaged = professionalRepository.findById(idProfessional).orElseThrow();
        professionalManaged.setUpdateDate(LocalDateTime.now());
        professionalManaged.setName(Util.nvl(professional.getName(), professionalManaged.getName()));
        professionalManaged.setPhone(Util.nvl(professional.getPhone(), professionalManaged.getPhone()));
        professionalManaged.setEmail(Util.nvl(professional.getEmail(), professionalManaged.getEmail()));

        professionalRepository.save(professionalManaged);
    }

    @Override
    public List<Professional> getAll() {
        return professionalRepository.findAll();
    }

    @Override
    public Professional getById(Long idProfessional) {
        Optional<Professional> professional = professionalRepository.findById(idProfessional);
        return professional.orElseThrow(() -> new ObjectNotFound("Object not found! Id " + idProfessional + ", Type: " + Professional.class.getName()));
    }

    private void checkIfEmailAlreadyExists(Professional professional) {
        Professional professionalManaged = professionalRepository.findByEmail(professional.getEmail());
        if (professionalManaged != null && !professionalManaged.equals(professional)) {
            throw new BadRequestException("Email já pertence a outra pessoa" + professional.getId());
        }
    }

    private void checkIfPhoneAlreadyExists(Professional professional) {
        Professional professionalManaged = professionalRepository.findByPhone(professional.getPhone());
        if (professionalManaged != null && !professionalManaged.equals(professional)) {
            throw new BadRequestException("Telefone já pertence a outra pessoa" + professional.getId());
        }
    }

    @Override
    public Professional findByName(String name) {
        if (Util.isEmpty(name)) {
            throw new BadRequestException("Nenhum valor informado");
        }

        Professional professional = professionalRepository.findByName(name);

        if (Util.isEmpty(professional)) {
            throw new ObjectNotFound("Nenhum resultado foi encontrado");
        }
        return professional;
    }

    @Override
    public List<Professional> findAllByName(String name) {
        if (Util.isEmpty(name)) {
            throw new BadRequestException("Nenhum valor informado");
        }

        List<Professional> professionals = professionalRepository.findAllByName(name);

        if (Util.isEmpty(professionals)) {
            throw new ObjectNotFound("Nenhum resultado foi encontrado");
        }
        return professionals;
    }

    @Override
    public JpaRepository<Professional, Long> getRepository() {
        return this.professionalRepository;
    }
}
