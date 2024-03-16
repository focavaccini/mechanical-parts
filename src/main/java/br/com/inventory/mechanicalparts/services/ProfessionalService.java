package br.com.inventory.mechanicalparts.services;

import br.com.inventory.mechanicalparts.controllers.interfaces.IAbstractService;
import br.com.inventory.mechanicalparts.entities.Professional;

import java.util.List;

public interface ProfessionalService extends IAbstractService<Professional, Long> {

    Professional insert(Professional professional);

    void update(Long idProfessional, Professional professional);

    List<Professional> getAll();

    Professional getById(Long idProfessional);

    Professional findByName(String name);

    List<Professional> findAllByName(String name);

    Professional findByUserId(Long userId);

}
