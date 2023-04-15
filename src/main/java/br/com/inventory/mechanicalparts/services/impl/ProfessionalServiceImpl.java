package br.com.inventory.mechanicalparts.services.impl;

import br.com.inventory.mechanicalparts.Utils.Util;
import br.com.inventory.mechanicalparts.dtos.ProfessionalDTO;
import br.com.inventory.mechanicalparts.entities.Professional;
import br.com.inventory.mechanicalparts.repositories.ProfessionalRepository;
import br.com.inventory.mechanicalparts.services.ProfessionalService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfessionalServiceImpl implements ProfessionalService {

    private ProfessionalRepository professionalRepository;

    @Override
    public Professional insert(Professional professional) {
        return professionalRepository.save(professional);
    }

    @Override
    public void update(Long idProfessional, Professional professional) {
        Professional professionalManaged = professionalRepository.findById(idProfessional).orElseThrow();

        professionalManaged.setName(Util.nvl(professional.getName(), professionalManaged.getName()));
        professionalManaged.setPhone(Util.nvl(professional.getPhone(), professionalManaged.getPhone()));
        professionalManaged.setEmail(Util.nvl(professional.getEmail(), professionalManaged.getEmail()));

        professionalRepository.save(professionalManaged);
    }

//    @Override
//    public ProfessionalDTO converterToDTO(Professional professional) {
//        ProfessionalDTO professionalDTO = new ProfessionalDTO();
//
//        professionalDTO.setNome(professional.getName());
//        professionalDTO.setEmail(professional.getEmail());
//        professionalDTO.setPhone(professional.getPhone());
//
//        return professionalDTO;
//    }
//
//    @Override
//    public Professional converterToEntity(ProfessionalDTO professionalDTO) {
//        Professional professional = new Professional();
//
//        professional.setName(professionalDTO.getNome());
//        professional.setEmail(professionalDTO.getEmail());
//        professional.setPhone(professionalDTO.getPhone());
//
//        return professional;
//    }

    @Override
    public JpaRepository<Professional, Long> getRepository() {
        return this.professionalRepository;
    }
}
