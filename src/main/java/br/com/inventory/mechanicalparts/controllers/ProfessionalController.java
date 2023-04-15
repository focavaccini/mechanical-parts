package br.com.inventory.mechanicalparts.controllers;

import br.com.inventory.mechanicalparts.controllers.interfaces.IProfessionalController;
import br.com.inventory.mechanicalparts.dtos.ProfessionalDTO;
import br.com.inventory.mechanicalparts.entities.Professional;
import br.com.inventory.mechanicalparts.services.ProfessionalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ProfessionalController extends AbstractController<ProfessionalService> implements IProfessionalController {

    private ProfessionalService professionalService;

    public ProfessionalDTO insert(@RequestBody ProfessionalDTO professionalDTO){
        Professional professional = convert(professionalDTO, Professional.class);
        professionalService.insert(professional);
        return convert(professional, ProfessionalDTO.class);
    }

    public void update(@PathVariable Long idProfessional, @RequestBody ProfessionalDTO professionalDTO){
        Professional professional = convert(professionalDTO, Professional.class);
        professionalService.update(idProfessional, professional);
    }
}
