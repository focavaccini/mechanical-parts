package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.dtos.ProfessionalDTO;
import br.com.inventory.mechanicalparts.entities.Professional;

public interface ProfessionalService extends IAbstractService<Professional, Long> {

    Professional insert(Professional professional);

    void update(Long idProfessional, Professional professional);

//    ProfessionalDTO converterToDTO(Professional professional);
//
//    Professional converterToEntity(ProfessionalDTO professionalDTO);
}
