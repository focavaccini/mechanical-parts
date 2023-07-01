package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.entities.Professional;
import br.com.inventory.mechanicalparts.exceptions.BadRequestException;
import br.com.inventory.mechanicalparts.exceptions.ObjectNotFound;
import br.com.inventory.mechanicalparts.repositories.ProfessionalRepository;
import br.com.inventory.mechanicalparts.services.ProfessionalService;
import br.com.inventory.mechanicalparts.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfessionalServiceImpl implements ProfessionalService {

    private ProfessionalRepository professionalRepository;

    private UserService userService;

    @Override
    public Professional insert(Professional professional) {
        Professional newProfessional = new Professional();


        try {
            userService.saveUser(professional.getUser());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        newProfessional = professionalRepository.save(professional);
        return newProfessional;
    }

    @Override
    public void update(Long idProfessional, Professional professional) {
        Professional professionalManaged = professionalRepository.findById(idProfessional).orElseThrow();

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
        return professional.orElseThrow(()-> new ObjectNotFound("Object not found! Id " + idProfessional + ", Type: " + Professional.class.getName()));
    }

    private void checkIfEmailAlreadyExists(Professional professional){
        Professional professionalManaged = professionalRepository.findByEmail(professional.getEmail());
        if(professionalManaged != null && !professionalManaged.equals(professional)){
            throw new BadRequestException("Email já pertence a outra pessoa" + professional.getId());
        }
    }
    private void checkIfPhoneAlreadyExists(Professional professional){
        Professional professionalManaged = professionalRepository.findByPhone(professional.getPhone());
        if(professionalManaged != null && !professionalManaged.equals(professional)){
            throw new BadRequestException("Telefone já pertence a outra pessoa" + professional.getId());
        }
    }

    @Override
    public JpaRepository<Professional, Long> getRepository() {
        return this.professionalRepository;
    }
}
