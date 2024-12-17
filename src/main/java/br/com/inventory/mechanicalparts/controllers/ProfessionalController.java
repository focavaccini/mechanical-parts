package br.com.inventory.mechanicalparts.controllers;

import br.com.inventory.mechanicalparts.controllers.interfaces.IProfessionalController;
import br.com.inventory.mechanicalparts.dtos.ProfessionalDTO;
import br.com.inventory.mechanicalparts.entities.Professional;
import br.com.inventory.mechanicalparts.services.ProfessionalService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProfessionalController extends AbstractController<ProfessionalService> implements IProfessionalController {

    private ProfessionalService professionalService;

    @CacheEvict(value = "professional", allEntries = true)
    public Professional insert(@RequestBody ProfessionalDTO professionalDTO) {
        Professional professional = convert(professionalDTO, Professional.class);
        return professionalService.insert(professional);
    }

    @CacheEvict(value = "professional", allEntries = true)
    public void update(@PathVariable Long idProfessional, @RequestBody ProfessionalDTO professionalDTO) {
        Professional professional = convert(professionalDTO, Professional.class);
        professionalService.update(idProfessional, professional);
    }

    @Override
    @Cacheable(value = "professional")
    public List<ProfessionalDTO> getAll() {
        return convert(professionalService.getAll(), ProfessionalDTO.class);
    }

    @Override
    @Cacheable(value = "professional", key = "#idProfessional")
    public ProfessionalDTO getById(Long idProfessional) {
        return convert(professionalService.getById(idProfessional), ProfessionalDTO.class);
    }

    @Override
    @Cacheable(value = "professional", key = "#name")
    public ProfessionalDTO findByName(String name) {
        return convert(professionalService.findByName(name), ProfessionalDTO.class);
    }

    @Override
    public List<ProfessionalDTO> findAllByName(String name) {
        return convert(professionalService.findAllByName(name), ProfessionalDTO.class);
    }
}
